# Basic Widget

### [Spinner](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/BasicWidget/app)

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/BasicWidget/%EC%9C%84%EC%A0%AF.png)

- 위젯 마다 설정해야되는 Listener 종류가 다르다

### 메인부분

```java
public class MainActivity extends AppCompatActivity{

    TextView textResult;
    ToggleButton toggleButton;
    CheckBox checkDog;
    CheckBox checkPig;
    CheckBox checkCow;
    RadioGroup radioGroup;
    RadioButton radioRed;
    RadioButton radioBlue;
    RadioButton radioGreen;
    RadioButton radioSpinner;
    ProgressBar progressBar;
    Switch mSwitch;
    SeekBar seekBar;
    TextView textSeekBarResult;
    RatingBar ratingBar;
    TextView rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // 위젯마다 Listener 설정
        toggleButton.setOnCheckedChangeListener(checkedChangeListener);
        mSwitch.setOnCheckedChangeListener(checkedChangeListener);

        checkDog.setOnCheckedChangeListener(checkboxListner);
        checkPig.setOnCheckedChangeListener(checkboxListner);
        checkCow.setOnCheckedChangeListener(checkboxListner);

        radioGroup.setOnCheckedChangeListener(radioListener);

        ratingBar.setOnRatingBarChangeListener(ratingbarListener);

        progressBar.setVisibility(View.INVISIBLE);
        // INVISIBLE -- 화면에 안보이는데 자리는 차지하고 있다
        // VISIBLE   -- 현재 화면에 보이는 상태
        // GONE      -- 화면에서 사라진 상태
```

### SeekBar
```java
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // seekBar의 상태가 바뀌었을 때
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              // 원이 이동한 만큼의 진행비율을 출력한다
                textSeekBarResult.setText(progress+"");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
```

### 레이팅바 리스너

```java
    RatingBar.OnRatingBarChangeListener ratingbarListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        // 레이팅바의 점수만큼을 출력
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            rate.setText(String.valueOf(rating));
        }
    };
```

### 라디오그룹 리스너

```java
    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        //클릭된 버튼에 따라 출력 다르게
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int radio_id) {
            switch(radio_id){
                case R.id.radioRed:
                    textResult.setText("빨간불이 켜졌습니다.");
                    break;
                case R.id.radioGreen:
                    textResult.setText("녹색불이 켜졌습니다.");
                    break;
                case R.id.radioBlue:
                    textResult.setText("파란불이 켜졌습니다.");
                    break;
                case R.id.radioSpinner:
                    Intent intent = new Intent(getApplicationContext(), SpinnerActivity.class);
                    startActivity(intent);
            }
        }
    };
```
### 체크박스 리스너

- 체크박스를 누르면 해당 정보가 list에 저장
- 출력시 list에 있는 정보 불러옴

```java
    ArrayList<String> checkedList = new ArrayList<>();
    CompoundButton.OnCheckedChangeListener checkboxListner
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            // 체크박스 처리
            // 체크 된 박스의 이름들을 어레이리스트에 add
            switch(compoundButton.getId()){
                case R.id.checkDog:
                    if(b){
                        checkedList.add("개");
                    }else{
                        checkedList.remove("개");
                    }
                    break;
                case R.id.checkPig:
                    if(b){
                        checkedList.add("돼지");
                    }else{
                        checkedList.remove("돼지");
                    }
                    break;
                case R.id.checkCow:
                    if(b){
                        checkedList.add("소");
                    }else{
                        checkedList.remove("소");
                    }
                    break;
            }
            //리스트에 저장된 이름들을 출력
            String checkedResult = "";
            for(String item : checkedList){
                checkedResult += item + " ";
            }

            textResult.setText(checkedResult + "(이)가 체크되었습니다.");
        }
    };
```

### 토글, 스위치 버튼
```java
    CompoundButton.OnCheckedChangeListener checkedChangeListener
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
            // 토글, 스위치 처리
            switch(compoundButton.getId()){
                case R.id.toggleButton:
                    if(check){
                        textResult.setText("토글버튼이 켜졌습니다");
                    }else{
                        textResult.setText("토글버튼이 꺼졌습니다");
                    }
                    break;
                case R.id.mSwitch:
                // swtich가 켜지면 프로그레스바 보이게
                // 꺼지면 안보이게
                    if(check){
                        progressBar.setVisibility(View.VISIBLE);
                    }else{
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    };

}
```
