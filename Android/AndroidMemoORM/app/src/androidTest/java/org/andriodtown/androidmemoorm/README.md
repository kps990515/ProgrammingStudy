# DetailActivity
- intent에서 넘어온 값을 꺼내 읽는다
- [FileUtil](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemoORM/app/src/test/java/org/andriodtown/androidmemoorm)을 이용해서 filename을 통해 bitmap을 읽어온다

```java
public class DetailActivity extends AppCompatActivity {

    private String filename;
    private String title;
    TextView dTitle;
    ImageView dImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dTitle = (TextView)findViewById(R.id.dTitle);
        dImage = (ImageView)findViewById(R.id.dImage);

        // 1. 리스트에서 넘어온 intent꺼내기
        Intent intent = getIntent();
        // 2. 인텐트에서 값을 꺼내서 담는다
        filename = intent.getStringExtra("filename");
        title = intent.getStringExtra("title");
        dTitle.setText(title);

        // 이미지를 화면에 주기 위해 파일명으로 bitmap읽어오기
        try {
            Bitmap bitmap = FileUtil.read(this,filename);
            dImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
