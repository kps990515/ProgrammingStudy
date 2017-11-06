package org.andriodtown.firebasemessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.andriodtown.firebasemessage.model.User;
import org.andriodtown.firebasemessage.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RelativeLayout addLayout;
    EditText edit_find;
    RecyclerView friendList;
    FirebaseDatabase database;
    DatabaseReference userRef;
    DatabaseReference friendRef;
    ProgressBar progressBar;
    FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user");
        friendRef = database.getReference("friend");
        friendRef.child(PreferenceUtil.getUserId(this)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> data = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    User friend = item.getValue(User.class);
                    data.add(friend);
                }
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        initView();
    }
    public void addFriend(View view){
        progressBar.setVisibility(View.VISIBLE);
        String email = edit_find.getText().toString().replace(".","_");
        // 이메일에 해당하는 노드가 있는지 검사
        if(email!=null && !"".equals(email)){
            userRef.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // 노드가 있으면
                    if(dataSnapshot.getChildrenCount()>0){
                        String key = dataSnapshot.getKey();
                        User friend = dataSnapshot.getValue(User.class);
                        // 내 노드 밑에 친구추가
                        friendRef.child(PreferenceUtil.getUserId(getBaseContext()))
                                    .child(key).setValue(friend);
                        // 친구 노드 밑에 나 추가
                        User me = new User();
                        me.id = PreferenceUtil.getUserId(getBaseContext());
                        me.name = PreferenceUtil.getString(getBaseContext(),"name");
                        me.email = PreferenceUtil.getString(getBaseContext(),"email");

                        friendRef.child(friend.id)
                                    .child(PreferenceUtil.getUserId(getBaseContext())).setValue(me);
                    }else{ // 없으면

                    }
                    progressBar.setVisibility(View.GONE);
                    addLayout.setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu); // R.menu.menu.xml을 옵션메뉴로 사용
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_add_friend:
                addLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.menu_signout:
                PreferenceUtil.setValue(this,"auto_sign","");
                Intent intent = new Intent(this,SigninActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
    private void initView(){
        addLayout = findViewById(R.id.addLayout);
        edit_find = findViewById(R.id.edit_find);
        progressBar = findViewById(R.id.progressBar);

        addLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout.setVisibility(View.GONE);
            }
        });
        friendList = findViewById(R.id.friendList);
        adapter = new FriendAdapter();
        friendList.setAdapter(adapter);
        friendList.setLayoutManager(new LinearLayoutManager(this));

    }

}
