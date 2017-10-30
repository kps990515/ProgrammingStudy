package org.andriodtown.firebasebasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements UserAdapter.Callback{

    FirebaseDatabase database;
    DatabaseReference bbsRef;
    DatabaseReference userRef;
    EditText edit_id;
    EditText edit_name;
    EditText edit_title;
    RecyclerView userList;
    RecyclerView bbsList;
    ValueEventListener valueEventListener;
    ValueEventListener bbsvalueEventListener;
    UserAdapter adapter;
    LinearLayoutManager manager;
    LinearLayoutManager bbsmanager;
    List<User> data;
    BbsAdapter bbsAdapter;
    /*
    DatabaseReference myRef;
    DatabaseReference rootRef;
    private EditText edit_msg;
    private TextView txt_msg;
    Button btn_send;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터베이스 connection
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("bbs");
        userRef = database.getReference("user");
        /*
        myRef = database.getReference("message");
        rootRef = database.getReference();

        writeNewUser("hong", "홍길동", "aaa@gmail.com", 18);

        initView();
        */
        initView();
        initListener();
    }

    public void signup(View v){
        String id = edit_id.getText().toString();
        String name = edit_name.getText().toString();

        User user = new User(name,"none",17);
        userRef.child(id).setValue(user);
    }

    public String user_id="";

    public void post(View v){
        if(user_id == null || "".equals(user_id)){
            Toast.makeText(this,"user를 선택하세요",Toast.LENGTH_SHORT);
        }else{
            String title = edit_title.getText().toString();
            Bbs bbs = new Bbs(title,"내용","2017-10-30",user_id);

            // 노드 생성
            String bbsKey = bbsRef.push().getKey();
            // 노드에 데이터 입력
            bbsRef.child(bbsKey).setValue(bbs);
            // 사용자 아이디에 게시글을 추가
            userRef.child(user_id).child("bbs").child(bbsKey).setValue(bbs);
        }
    }
    private void initView(){
        edit_id = findViewById(R.id.edit_id);
        edit_name = findViewById(R.id.edit_name);
        edit_title = findViewById(R.id.edit_title);

        userList = findViewById(R.id.userList);
        bbsList = findViewById(R.id.bbsList);

        adapter = new UserAdapter(this);
        userList.setAdapter(adapter);
        manager = new LinearLayoutManager(getApplicationContext());
        userList.setLayoutManager(manager);

        bbsAdapter = new BbsAdapter();
        bbsList.setAdapter(bbsAdapter);
        bbsmanager = new LinearLayoutManager(getApplicationContext());
        bbsList.setLayoutManager(bbsmanager);
    }

    private void initListener(){
        valueEventListener = (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                // 변경사항이 스냅샷 형태로 넘어오면
                // 해당 스냅샷의 하위 노드를 배열로 꺼내서 사용한다.
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String id = snapshot.getKey();
                    // 하위 노드 아래에 리스트 형태의 또 다른 노드가 있으면
                    // 부가적인 처리가 필요하다
                    if(snapshot.hasChild("bbs")){
                        User user = new User();
                        Map map = (HashMap) snapshot.getValue();
                        user.username = (String) map.get("username");
                        user.email = (String) map.get("email");
                        user.user_id = id;

                        // 하위노드의 스냅샷 꺼내기
                        DataSnapshot bbsSnapShot = snapshot.child("bbs");
                        user.bbs = new ArrayList<>();
                        // 하위 노드에 리스트가 존재하면 해당 리스트를
                        // 배열로 꺼내서 위의 방법처럼 사용한다
                        for(DataSnapshot item : bbsSnapShot.getChildren()) {
                            Bbs bbs = item.getValue(Bbs.class);
                            user.bbs.add(bbs);
                        }
                        data.add(user);
                    }else{
                        User user = snapshot.getValue(User.class);
                        user.user_id = id;
                        data.add(user);
                    }
                }
                adapter.refresh(data);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        userRef.addValueEventListener(valueEventListener);

        bbsvalueEventListener = (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Bbs> bbsdata = new ArrayList<>();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Bbs bbs = snapshot.getValue(Bbs.class);
                    bbs.id = snapshot.getKey();
                    bbsdata.add(bbs);
                }
                // 데이터 어뎁터에 반영
                // 아답터를 notify
                bbsAdapter.refresh(bbsdata);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        bbsRef.addValueEventListener(bbsvalueEventListener);
    }


/*
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            txt_msg.setText("");
            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                String msg = snapshot.getValue(String.class);
                txt_msg.setText(txt_msg.getText() + "\n" + msg);
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
*/
    @Override
    protected void onResume() {
        super.onResume();
        userRef.addValueEventListener(valueEventListener);
        bbsRef.addValueEventListener(bbsvalueEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        userRef.removeEventListener(valueEventListener);
        bbsRef.removeEventListener(bbsvalueEventListener);
    }

    @Override
    public void setUserid(String userid) {
        this.user_id = userid;
    }
/*
    public void send(View view){
        String msg = edit_msg.getText().toString();
        if(msg==null || "".equals(msg)){ // 키 값에 아무것도 없으면 생성이 안되므로 임의값 넣어주기
            msg="none";
        }
        // 노드 생성
        String key = myRef.push().getKey();
        // 노드에 값넣기
        myRef.child(key).setValue(msg);
    }

    private void writeNewUser(String userId, String name, String email, int age) {
        User user = new User(name, email, age);

        rootRef.child("users") // 레퍼런스를 가져오고
                .child(userId) // 추가될 노드를 생성
                .setValue(user); // 추가된 노드에 값을 입력
    }
    */
}
