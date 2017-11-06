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
    String myId;
    String friendId;
    String myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        myId = PreferenceUtil.getUserId(this);
        myName = PreferenceUtil.getString(this,"name");
        Intent intent = getIntent();
        friendId = intent.getStringExtra("friend_id");
        database = FirebaseDatabase.getInstance();
        // node 구조
        // /chat / myid = dsf@naver_com / friendid = dsfsd@gmail_com / msgkey 자동생성
        msgRef = database.getReference("chat/"+myId+"/"+friendId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

    }
    public void send(View view){
        String text = edit_msg.getText().toString();
        if(text!=null && !"".equals(text)) {
            String msgKey = msgRef.push().getKey();
            Msg msg = new Msg();
            msg.user_id = myId;
            msg.msg = text;
            msg.name = myName;
            msg.time = System.currentTimeMillis()+"";
            msgRef.child(msgKey).setValue(msg);
        }
    }

    private void initView(){
        msgList = findViewById(R.id.msgList);
        edit_msg = findViewById(R.id.edit_msg);
        adapter = new MessageAdapter(this);
        msgList.setAdapter(adapter);
        msgList.setLayoutManager(new LinearLayoutManager(this));
        msgRef.addValueEventListener(new ValueEventListener() {
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
