package org.andriodtown.firebasebasic2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StorageActivity extends AppCompatActivity implements UserAdapter.Callback{
    // 파일저장소
    private StorageReference storageRef;
    // 실시간 데이터베이스
    FirebaseDatabase database;
    DatabaseReference userRef;

    private EditText edit_msg;
    private RecyclerView userList;
    private TextView txt_id;
    private TextView txt_token;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        storageRef = FirebaseStorage.getInstance().getReference();
        database  = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");
        initView();

    }

    private void initView() {
        edit_msg = findViewById(R.id.edit_msg);
        txt_id = findViewById(R.id.txt_id);
        txt_token = findViewById(R.id.txt_token);
        userList = findViewById(R.id.userList);
        adapter = new UserAdapter(this);
        userList.setAdapter(adapter);
        userList.setLayoutManager(new LinearLayoutManager(this));

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> data = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    // value부분만 가져와서 User클래스로 캐스팅 후 데이터에 추가
                    User user = item.getValue(User.class);
                    data.add(user);
                }
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void send(View view){
        String token = txt_token.getText().toString();
        String msg = edit_msg.getText().toString();

        if(token==null || "".equals(token)){
            Toast.makeText(this,"받는 사람을 선택하세요",Toast.LENGTH_SHORT).show();
            return;
        }
        if(msg==null || "".equals(msg)){
            Toast.makeText(this,"메세지를 입력하세요",Toast.LENGTH_SHORT).show();
            return;
        }
        String json = "{\"to\":\""+token+"\", \"msg\":\""+msg+"\"}";
        //Retrofit 선언
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.0.9:8090/")
                                .build();
        // 인터페이스와 결합
        IRetro service = retrofit.create(IRetro.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),json);
        // 서비스로 서버 연결준비
        Call<ResponseBody> remote = service.sendNotification(body);
        // 실제 연결 후 데이터 처리
        remote.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody data = response.body();
                    Toast.makeText(StorageActivity.this,data.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Retroerror", t.getMessage());
            }
        });

    }

    // 파일 탐색기 호출
    public void chooseFile(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*"); // 갤러리 image/*, 동영상 video/*
        startActivityForResult(intent.createChooser(intent, "Select App"), 999);
    }

    // 파일이 선택되면 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            upload(uri);
        }
    }

    public void upload(Uri file){
        // 실제 파일이 있는 경로
        // Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        // 파이어베이스의 스토리지 node
        String temp[] = file.getPath().split("/");
        String filename = temp[temp.length-1];
        StorageReference riversRef = storageRef.child("files/"+filename);

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(StorageActivity.this, exception.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void setIdAndToken(String id, String token) {
        txt_id.setText(id);
        txt_token.setText(token);
    }
}
