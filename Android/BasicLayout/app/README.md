# 계산기

<img src="https://github.com/kps990515/ProgrammingStudy/blob/master/Android/BasicLayout/app/Screenshot_1505304249.png" width=20%>
<img src="https://github.com/kps990515/ProgrammingStudy/blob/master/Android/BasicLayout/app/Screenshot_1505304255.png" width=20%>

### [OnClickListner를 implements하는 코드](https://github.com/javafa/4th_BasicLayout/blob/master/app/src/main/java/com/veryworks/android/basiclayout/Calculator.java)

### xml
- Linear Layout으로 구성
- 전체 Layout은 Vertical
- 내부는 총 8개의 Horizontal
- Calculator & 계산식 & 결과값이 나오는 textview는 전체 Layout에
- 버튼 4개마다 하나의 Horizontal
- weight를 통해 크기 조절

### Activity 코드

#### 클래스
- 괄호에 묶인 숫자 & ''묶인 연산자 저장하는 2개의 ArrayList
- 괄호에 안묶인 숫자 & 연산자 저장하는 2개의 ArrayList
```java
public class activity_calculator extends AppCompatActivity  {
    private TextView cal; // 결과값이 나타나는 textview
    private ArrayList <String> numList = new ArrayList<>(); // 괄호에 안묶인 숫자를 저장하는 list
    private ArrayList <String> opList = new ArrayList<>();  // 괄호에 안묶인 연산자를 저장하는 list
    private ArrayList <String> firstNumList = new ArrayList<>(); // 괄호에 묶인 숫자를 저장하는 list
    private ArrayList <String> firstOpList = new ArrayList<>();  // 괄호에 묶인 연산자를 저장하는 list
    String s;
    int start;
```

#### Oncreate
- 각 버튼과 textview를 선언하고 OnClickListner를 set
```java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);
    //메인 레이아웃이 생성됨
    //계산 결과값이 나오는 textview
    cal = ((TextView) findViewById(R.id.txt_cal));
    //버튼들을 찾아서 OnClicklister를 설정
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
    findViewById(R.id.btn_multi).setOnClickListener(onClickListener);
    findViewById(R.id.btn_divide).setOnClickListener(onClickListener);
    findViewById(R.id.btn_clear).setOnClickListener(onClickListener);
    findViewById(R.id.btn_cal).setOnClickListener(onClickListener);
    findViewById(R.id.btn_left).setOnClickListener(onClickListener);
    findViewById(R.id.btn_right).setOnClickListener(onClickListener);
    findViewById(btn_dot).setOnClickListener(onClickListener);
}
```

#### OnClickListner를 구현
```java
    View.OnClickListener onClickListener = new View.OnClickListener() {
```

##### 버튼이 눌렸을 때 기능
- 0~9는 단지 계산식에 숫자를 출력
- 단 00000,099999는 안되도록 예외처리
- 연산자의 경우 연산자사이에 숫자를 numList에 add
- 연산자는 opList에 add
- 괄호는 '('의 경우만 연산자같이 구현
```java
@Override
public void onClick(View v) {
    // 각 버튼들이 클릭되면 실행되는 기능 구현
    switch (v.getId()) {
        case btn_one:   textAdd("1");   break;
        case btn_two:   textAdd("2");   break;
        case btn_three: textAdd("3");   break;
        case btn_four:  textAdd("4");   break;
        case btn_five:  textAdd("5");   break;
        case btn_six:   textAdd("6");   break;
        case btn_seven: textAdd("7");   break;
        case btn_eight: textAdd("8");   break;
        case btn_nine:  textAdd("9");   break;
        case btn_zero:  textAdd("0");   break;
        case btn_plus:
            s=cal.getText().toString(); s=addNum(s);    exist(s);     textAdd("+");   opList.add("+");    break;
        case btn_minus:
            s=cal.getText().toString(); s=addNum(s);    exist(s);     textAdd("-");   opList.add("-");    break;
        case btn_divide:
            s=cal.getText().toString(); s=addNum(s);    exist(s);     textAdd("/");   opList.add("/");    break;
        case btn_multi:
            s=cal.getText().toString(); s=addNum(s);    exist(s);     textAdd("*");   opList.add("*");    break;
        case btn_clear:
            cal.setText("");            opList.clear(); numList.clear();  firstOpList.clear(); firstNumList.clear();
            ((TextView)findViewById(R.id.txt_result)).setText("");  break;
        case btn_dot:
            s=cal.getText().toString(); s=addNum(s);    textAdd(".");   opList.add(".");    break;
        case btn_left:
            s=cal.getText().toString(); textAdd("(");   opList.add("(");    break;
        case btn_right:
            s=cal.getText().toString(); s=addNum(s);     exist(s);    textAdd(")");   opList.add(")");    break;
        case btn_cal:
            s=cal.getText().toString();
            s=addNum(s);
            numList.add(s);
            textAdd("=");
```

##### 괄호 유무에 따른 계산식
- 괄호안의 계산
- 연산자가 하나가 아니라면 check() & checksort() & sort() & +,- 계산
- 연산자가 하나라면 sort() & + - 계산
```java
// 만약 연산자가 하나이상이면
if(opList.size()!=1) {
    check(); //괄호가 있는지 확인 + 괄호 안에 있는 숫자, 연산자분리
    checksort(); // '*' '/"의 경우 먼저계산
    double sum=Double.parseDouble(firstNumList.get(0));
    // 괄호안에 있는 나머지것들 계산
    for(int i=0; i<firstOpList.size(); i++)
    {
        switch(firstOpList.get(i)){
            case "+":
                sum += Double.parseDouble(firstNumList.get(i+1));
                break;
            case "-":
                sum -= Double.parseDouble(firstNumList.get(i+1));
                break;
            case "*":
                sum *= Double.parseDouble(firstNumList.get(i+1));
                break;
            case "/":
                sum /= Double.parseDouble(firstNumList.get(i+1));
                break;
        }
    }
    // 계산값을 전체 숫자list에 전달
    numList.add(start,sum+"");
    // 전체에서 * / 먼저 계산
    sort();
}
else // 괄호 없는 경우
{
    sort();
}
```

##### 괄호 안의 결과값 + 괄호 밖 계산

```java
double sum=Double.parseDouble(numList.get(0));
      for(int i=0; i<opList.size(); i++)
        {
            switch(opList.get(i)){
            case "+":
            sum += Double.parseDouble(numList.get(i+1));
            break;
            case "-":
            sum -= Double.parseDouble(numList.get(i+1));
            break;
            case "*":
            sum *= Double.parseDouble(numList.get(i+1));
            break;
            case "/":
            sum /= Double.parseDouble(numList.get(i+1));
            break;
            }
    }
```

##### 결과값 출력
- int형으로 치환되면 정수출력
- 안되면 소숫점 출력
```java
//int형으로 치환가능하면 정수형으로 출력
  if((int)sum==sum)
  {
    ((TextView)findViewById(R.id.txt_result)).setText((int)sum+"");
  }
//치환불가하면 소숫점으로 출력
else
  {
    ((TextView)findViewById(R.id.txt_result)).setText(sum+"");
  }
  cal.setText("");
  // list들 비워주기
  opList.clear();
  numList.clear();
  firstNumList.clear();
  firstOpList.clear();
  }
```

##### textadd
- 계산식에 숫자,연산자 받아오는 함수
- 중복 입력불가하게 처리
```java
private void textAdd(String num)
{
    s=cal.getText().toString();//3*(3+5) [3 3 5] [* ( + ) ]
    s=addNum(s);
    if(s.indexOf("0")==0)
    {
        String temp = cal.getText().toString();
        cal.setText(temp.substring(0,temp.length()-1)+num);
    }
    else
    {
        cal.setText(cal.getText().toString() + num);
    }
}
```

##### exist
- 숫자를 list에 받아올 때 공백예외처리
```java
public void exist(String s)
{
    if(!s.equals(""))
    {
        numList.add(s);
    }
}
```

##### addNum
- 연산자가 입력될때마다 전 연산자 뒤에있는 숫자를 list에 저장
```java
public String addNum(String s)
{
    int indexp = s.lastIndexOf("+");
    int indexm = s.lastIndexOf("-");
    int indexs = s.lastIndexOf("*");
    int indexd = s.lastIndexOf("/");
    int indexl = s.lastIndexOf("(");
    int indexr = s.lastIndexOf(")");
    int []indexArray = {indexp, indexm, indexs, indexd, indexl, indexr};
    for(int i=0; i<5; i++)
    {
        if(indexArray[i]>indexArray[i+1])
        {
            int temp = indexArray[i+1];
            indexArray[i+1] = indexArray[i];
            indexArray[i] = temp;
        }
    }
    int index = indexArray[5];
    if(index==-1)
    {
        return s;
    }
    else {
        String numString = s.substring(index+1);
        return numString;
    }
}
```

##### check
- 괄호가 있으면 전체list들에서 숫자,연산자를 다른 list로 분리
```java
public void check()
    {
        //괄호 있을 시
        int count=0;
        start = opList.indexOf("(");
        int close = opList.indexOf(")");
        if(start!=-1&&close!=-1) {
            for (int i = start; i < close; i++) {
                firstNumList.add(numList.get(i-count));
                numList.remove(i-count);
                count++;
            }
            count=0;
            for (int i = start + 1; i < close; i++) {
                firstOpList.add(opList.get(i-count));
                opList.remove(i-count);
                count++;
            }
            opList.remove("(");
            opList.remove(")");
            // 괄호 제거 종료
        }
    }
```

##### checksort
- 분리된 list에서 * / 먼저 계산해주기
```java
public void checksort() {
     int count = 0;
     int length = firstOpList.size();

     for (int j = 0; j < length; j++) {
         if (j - count < firstOpList.size()) {
             String s = firstOpList.get(j - count);
             if (s.equals("/")) {
                 if (count + 1 == length) {
                     break;
                 }
                 firstNumList.set(j - count, Double.toString(Double.parseDouble(firstNumList.get(j - count)) / Double.parseDouble(firstNumList.get(j + 1 - count))));
                 firstNumList.remove(j + 1 - count);
                 firstOpList.remove(j - count);
                 count++;
             }

             if (s.equals("*")) {
                 if (count + 1 == length) {
                     break;
                 }
                 firstNumList.set(j - count, Double.toString(Double.parseDouble(firstNumList.get(j - count)) * Double.parseDouble(firstNumList.get(j + 1 - count))));
                 firstNumList.remove(j + 1 - count);
                 firstOpList.remove(j - count);
                 count++;
             }
         } else {
             break;
         }
     }
 }
```

##### sort
- 괄호안에 없는 것들 * / 먼저 계산해주기
```java
public void sort()
  {
      int count=0;
      int length=opList.size();

      for(int j=0; j<length; j++)
      {
              if(j-count<opList.size()) {
                  String s = opList.get(j-count);
                  if (s.equals("/"))
                  {
                      if(count+1==length)
                      {
                          break;
                      }
                      numList.set(j-count,Double.toString(Double.parseDouble(numList.get(j - count)) / Double.parseDouble(numList.get(j + 1 - count))));
                      numList.remove(j + 1 - count);
                      opList.remove(j - count);
                      count++;

                  }
                  if (s.equals("*"))
                  {
                      if(count+1==length)
                      {
                          break;
                      }
                      numList.set(j-count, Double.toString(Double.parseDouble(numList.get(j - count)) * Double.parseDouble(numList.get(j + 1 - count))));
                      numList.remove(j + 1 - count);
                      opList.remove(j - count);
                      count++;
                  }
              }
              else{
                  break;
              }
      }

  }
```
