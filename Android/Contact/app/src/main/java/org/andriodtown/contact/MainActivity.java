package org.andriodtown.contact;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.andriodtown.contact.model.Contact;
import org.andriodtown.contact.util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

/*
Content Resolver 사용하기
1. Content Resolver를 불러오기
2. 데이터 URI 정의 <- 일종의 DB에서의 테이블 이름
3. 데이터 URI에서 가져올 컬럼명 정의
    - 조건절을 정의할 수도 있음
4. Content Resolver로 쿼리한 데이터를 Cursor에 담는다
5. Cursor에 담긴 데이터를 반복문을 돌면서 처리한다
 */

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE = 999;
    private String permissions[] = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };
    PermissionUtil pUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pUtil = new PermissionUtil(REQ_CODE,permissions);
        if(pUtil.checkPermission(this)){
            init();
        }
    }

    public void init(){
        List<Contact> data = load();
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(pUtil.afterPermissionResult(requestCode, grantResults)){
            init();
        }else{
            Toast.makeText(getApplicationContext(), "권한필요!",Toast.LENGTH_SHORT).show();

        }
    }

    private List<Contact> load() {
        List<Contact> contacts = new ArrayList<>();
        // 1. Content Resolver 불러오기
        ContentResolver resolver = getContentResolver();

        // 2. 데이터 URI 정의
        // 전화번호 uri : ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        // 주소록 uri : ContactsContract.Contacts.CONTENT_URI;
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 3. 가져올 컬럼 정의
        // 이미 정의된 컬럼들이 존재한다
        String projection[] = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // 4. 쿼리 결과를 Cursor에 담기
        Cursor cursor = resolver.query(uri, projection, null, null, null);

        // 5. Cursor 반복처리
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Contact contact = new Contact();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    switch (cursor.getColumnName(i)) {
                        case ContactsContract.CommonDataKinds.Phone.CONTACT_ID:
                            contact.setId(cursor.getInt(i));
                            break;
                        case ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME:
                            contact.setName(cursor.getString(i));
                            break;
                        case ContactsContract.CommonDataKinds.Phone.NUMBER:
                            contact.setNumber(cursor.getString(i));
                            break;
                        }
                    }
                contacts.add(contact);
                }
            }
        return contacts;
        }
    }


