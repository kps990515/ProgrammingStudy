package org.andriodtown.basiclayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static org.andriodtown.basiclayout.R.id.btn_cal;
import static org.andriodtown.basiclayout.R.id.btn_clear;
import static org.andriodtown.basiclayout.R.id.btn_divide;
import static org.andriodtown.basiclayout.R.id.btn_eight;
import static org.andriodtown.basiclayout.R.id.btn_five;
import static org.andriodtown.basiclayout.R.id.btn_four;
import static org.andriodtown.basiclayout.R.id.btn_minus;
import static org.andriodtown.basiclayout.R.id.btn_multi;
import static org.andriodtown.basiclayout.R.id.btn_nine;
import static org.andriodtown.basiclayout.R.id.btn_one;
import static org.andriodtown.basiclayout.R.id.btn_plus;
import static org.andriodtown.basiclayout.R.id.btn_seven;
import static org.andriodtown.basiclayout.R.id.btn_six;
import static org.andriodtown.basiclayout.R.id.btn_three;
import static org.andriodtown.basiclayout.R.id.btn_two;
import static org.andriodtown.basiclayout.R.id.btn_zero;

public class activity_calculator extends AppCompatActivity {
    private TextView cal;
    private int sum;
    private ArrayList <String> numList = new ArrayList<>();
    private ArrayList <String> opList = new ArrayList<>();
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        cal = ((TextView) findViewById(R.id.txt_cal));

        findViewById(R.id.btn_one).setOnClickListener(onClickListener);
        findViewById(R.id.btn_two).setOnClickListener(onClickListener);
        findViewById(R.id.btn_three).setOnClickListener(onClickListener);
        findViewById(R.id.btn_four).setOnClickListener(onClickListener);
        findViewById(R.id.btn_five).setOnClickListener(onClickListener);
        findViewById(R.id.btn_six).setOnClickListener(onClickListener);
        findViewById(R.id.btn_seven).setOnClickListener(onClickListener);
        findViewById(R.id.btn_eight).setOnClickListener(onClickListener);
        findViewById(R.id.btn_nine).setOnClickListener(onClickListener);
        findViewById(R.id.btn_zero).setOnClickListener(onClickListener);
        findViewById(R.id.btn_plus).setOnClickListener(onClickListener);
        findViewById(R.id.btn_minus).setOnClickListener(onClickListener);
        findViewById(btn_multi).setOnClickListener(onClickListener);
        findViewById(btn_divide).setOnClickListener(onClickListener);
        findViewById(R.id.btn_clear).setOnClickListener(onClickListener);
        findViewById(R.id.btn_cal).setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            // 액티비티(메이저 컴포넌트) 실행
            // 1. 인텐트 (시스템에 전달되는 메시지 객체) 생성

            switch (v.getId()) {
                case btn_one:
                    textAdd("1");
                    break;
                case btn_two:
                    textAdd("2");
                    break;
                case btn_three:
                    textAdd("3");
                    break;
                case btn_four:
                    textAdd("4");
                    break;
                case btn_five:
                    textAdd("5");
                    break;
                case btn_six:
                    textAdd("6");
                    break;
                case btn_seven:
                    textAdd("7");
                    break;
                case btn_eight:
                    textAdd("8");
                    break;
                case btn_nine:
                    textAdd("9");
                    break;
                case btn_zero:
                    textAdd("0");
                    break;
                case btn_plus://3+5+6
                    s=cal.getText().toString();
                    s=addNum(s);
                    numList.add(s);
                    textAdd("+");
                    opList.add("+");
                    break;
                case btn_minus:
                    s=cal.getText().toString();
                    s=addNum(s);
                    numList.add(s);
                    textAdd("-");
                    opList.add("-");
                    break;
                case btn_divide:
                    s=cal.getText().toString();
                    s=addNum(s);
                    numList.add(s);
                    textAdd("/");
                    opList.add("/");//8-2/2+3*5=
                 /*   private void textAdd(String num)
                {
                    cal.setText(cal.getText().toString() + num);
                }
                */
                    break;
                case btn_multi:
                    s=cal.getText().toString();
                    s=addNum(s);
                    numList.add(s);
                    textAdd("*");
                    opList.add("*");
                    break;
                case btn_clear:
                    cal.setText("");
                    opList.clear();
                    numList.clear();
                    ((TextView)findViewById(R.id.txt_result)).setText("");
                    break;
                case btn_cal:
                    s=cal.getText().toString();
                    s=addNum(s);
                    numList.add(s);
                    textAdd("=");
                    double sum=Integer.parseInt(numList.get(0));
                    for(int i=0; i<opList.size(); i++)
                    {
                        switch(opList.get(i)){
                            case "+":
                                sum += Integer.parseInt(numList.get(i+1));
                                break;
                            case "*":
                                sum *= Integer.parseInt(numList.get(i+1));
                                break;
                            case "-":
                                sum -= Integer.parseInt(numList.get(i+1));
                                break;
                            case "/":
                                sum = sum / Integer.parseInt(numList.get(i+1));
                                break;
                        }
                    }
                    if((int)sum==sum)
                    {
                        ((TextView)findViewById(R.id.txt_result)).setText((int)sum+"");
                    }
                    else
                    {
                        ((TextView)findViewById(R.id.txt_result)).setText(sum+"");
                    }
                    cal.setText("");
                    opList.clear();
                    numList.clear();

            }
        }
    };
    private void textAdd(String num)
    {
        cal.setText(cal.getText().toString() + num);
    }
    public String addNum(String s)
    {
        int indexp = s.lastIndexOf("+");
        int indexm = s.lastIndexOf("-");
        int indexs = s.lastIndexOf("*");
        int indexd = s.lastIndexOf("/");
        int []indexArray = {indexp, indexm, indexs, indexd};
        for(int i=0; i<3; i++)
        {
            if(indexArray[i]>indexArray[i+1])
            {
                int temp = indexArray[i+1];
                indexArray[i+1] = indexArray[i];
                indexArray[i] = temp;
            }
        }
        int index = indexArray[3];
        if(index==-1)
        {
            return s;
        }
        else {
            String numString = s.substring(index+1);
            return numString;
        }
    }

}
