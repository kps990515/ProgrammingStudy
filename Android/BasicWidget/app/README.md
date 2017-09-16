# Spinner

```java
package org.andriodtown.basicwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    TextView txtResult;
    String data [] = {"월","화","수","목","금","토","일",};
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        txtResult = (TextView) findViewById(R.id.txtResult);
        // 1. 스피너에 입력될 데이터가 정의
        list.add("월");
        list.add("화");
        list.add("수");

        // 2. 스피너와 데이터를 연결하는 어뎁터
        // (데이터가 어떤 형식으로 스피너에 출력될지, 데이터)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        // 3. 어댑터와 스피너를 연결
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        // 4. 스피너에 리스너를 달아준다
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = data[position];
                txtResult.setText(selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

```
