package org.andriodtown.firebasemessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.andriodtown.firebasemessage.model.Msg;
import org.andriodtown.firebasemessage.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView msgList;
    EditText edit_msg;
    MessageAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference msgRef;
    DatabaseReference friendRef;
    String myId="";
    String friendId="";
    String myName="";
    String chatId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        friendId = intent.getStringExtra("friend_id");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myId = PreferenceUtil.getUserId(this);
        myName = PreferenceUtil.getString(this,"name");

        // - node의 구조
        // / chat / myid@naver_com / friend@naver_com
        msgRef = database.getReference("chat");
        // / friend / 내아이디 / 친구아이디
        friendRef= database.getReference("friend").child(myId).child(friendId);

        // chat_id가 생성되어있지 않으면 생성
        friendRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("chat_id")){
                    chatId = (String) dataSnapshot.child("chat_id").getValue();
                }else{
                    chatId = msgRef.push().getKey();
                    //  나의 친구목록에 있는 친구의 아이디 밑에 채팅방 번호를 부여
                    friendRef.child("chat_id").setValue(chatId);
                    // 친구의 친구목록에 있는  나의 아이디 밑에 채팅방 번호를 부여
                    database.getReference("friend/"+friendId+"/"+myId+"/chat_id").setValue(chatId);
                }
                initView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void send(View view){
        String text = edit_msg.getText().toString();
        if(text != null && !"".equals(text)) {
            Msg msg = new Msg();
            msg.user_id = myId;
            msg.msg = text;
            msg.name = myName;
            msg.time = System.currentTimeMillis()+"";

            // chat / 채팅방번호 / msg / 메시지 번호
            // 메시지 번호 생성
            String msgKey = msgRef.child(chatId).push().getKey();
            // 메시지 데이터 입력
            msgRef.child(chatId).child(msgKey).setValue(msg);
            // 친구노드에 채팅방 번호 추가
            //friendRef.child("chat_id").setValue(chat_id);
            edit_msg.setText("");
        }
    }

    private void initView(){
        msgList = findViewById(R.id.msgList);
        edit_msg = findViewById(R.id.edit_msg);

        adapter = new MessageAdapter(this);
        msgList.setAdapter(adapter);
        msgList.setLayoutManager(new LinearLayoutManager(this));
        msgRef.child(chatId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Msg> data = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    Msg msg = item.getValue(Msg.class);
                    data.add(msg);
                }
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
