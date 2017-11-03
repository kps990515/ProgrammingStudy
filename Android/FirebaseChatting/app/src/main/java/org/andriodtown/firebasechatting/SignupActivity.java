package org.andriodtown.firebasechatting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.andriodtown.firebasechatting.model.User;
import org.andriodtown.firebasechatting.util.VerficiationUtil;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    EditText edit_id;
    EditText edit_pw;
    EditText edit_email;
    EditText edit_phone;
    Button btn_signupup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user");
        initView();
    }

    public void signupup(View view){
        final String pw = edit_pw.getText().toString();
        final String id = edit_id.getText().toString();
        final String email = edit_email.getText().toString();
        final String phone = edit_phone.getText().toString();
        // 1. 인증메일
        mAuth.createUserWithEmailAndPassword(email,pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser fuser = mAuth.getCurrentUser();
                            fuser.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                                            dialogBuilder.setTitle("Notice");
                                            dialogBuilder.setMessage("인증메일 발송");

                                            dialogBuilder.setCancelable(false);
                                            dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    finish();
                                                }
                                            });

                                            AlertDialog dialog = dialogBuilder.create();
                                            dialog.show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });
                            // 2. 사용자등록
                            User user = new User(id,email, "", phone);
                            userRef.child(fuser.getUid()).setValue(user);

                        }else{
                            Log.e("Auth","creation fail");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Auth",e.getMessage());
                    }
                });
    }

    boolean checkEmail = false;
    boolean checkId = false;
    boolean checkPw = false;
    boolean checkPhone = false;
    private void enableSignupButton(){
        if(checkEmail && checkId && checkPhone && checkPw){
            btn_signupup.setEnabled(true);
        }else{
            btn_signupup.setEnabled(false);
        }
    }

    private void initView(){
        edit_id = findViewById(R.id.edit_id);
        edit_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkId = VerficiationUtil.isValidId(s.toString());
                enableSignupButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_pw = findViewById(R.id.edit_pw);
        edit_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPw = VerficiationUtil.isValidPassword(s.toString());
                enableSignupButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_email = findViewById(R.id.edit_email);
        edit_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmail = VerficiationUtil.isValidEmail(s.toString());
                enableSignupButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_phone = findViewById(R.id.edit_phone);
        edit_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPhone = VerficiationUtil.isValidPhone(s.toString());
                enableSignupButton();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_signupup = findViewById(R.id.btn_signupup);
    }
}
