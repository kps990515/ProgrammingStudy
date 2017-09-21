# ListAdapter

### 설명
 1. 데이터와 리스트뷰 연결위한 커스텀 어뎁터
 2. 어뎁터에 holder를 만들어 정보를 담아 중복생성방지


### ListAdapter.class
- static으로 Arraylist생성
- 생성자로 data,context 입력
- list_item.xml을 view에 불러오기
- holder를 붙어서 data를 가져와 memo의 개별 값 세팅

```java

public class ListAdapter extends BaseAdapter{
    // 이래야 DetailActivity에서 data를 받을 수 있음
    // data 중복 사용방지
    public static ArrayList<Memo>data;
    Context context;
    // 생성자
    public ListAdapter(Context context, ArrayList<Memo> data){
        this.data=data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder = null;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        }
        else{
            holder = (Holder)view.getTag();
        }
        //값을 세팅
        // 1. 컬렉션 구조의 저장소에 객체 단위로 꺼내두는게 편하다
        Memo memo = data.get(position);
        // 2. 홀더의 위젯에 데이터를 입력
        holder.setTextno(memo.getNo());
        holder.setTexttitle(memo.getTitle());
        holder.setTextDate(memo.getDatetime());
        holder.setPosition(position);
        holder.setAuthor(memo.getAuthor());
        holder.setContent(memo.getContent());

        return view;
    }
}
```

### holder class
- private으로 변수값 설정한 이유는 외부접근 방지
- 그래서 getter setter필요
- main.xml에 표시할 title,no,date를 위해 textView 담아줌
- 리스트뷰 클릭시 세부페이지로 넘어가는 intent생성
- 정보는 position만 보내줌(data static이라)
- [DetailActivity](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemo/app/src) 이동
```java
class Holder{
    private int position;
    private String author;
    private String content;
    private TextView textNo;
    private TextView textTitle;
    private TextView textDate;

    public void setAuthor(String author){
        this.author = author;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setTextno(int no) {
        textNo.setText(no+"");
    }

    public void setTexttitle(String title) {
       textTitle.setText(title);
    }

    public void setTextDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:MM");
        String result = sdf.format(date);
        textDate.setText(result);
    }

    public void setPosition(int position){
        this.position = position;
    }

    public Holder(View view){
        textNo = (TextView) view.findViewById(textno);
        textTitle = (TextView) view.findViewById(texttitle);
        textDate = (TextView) view.findViewById(R.id.textdatetime);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("position", position);
                /*
                intent.putExtra("title", textTitle.getText());
                intent.putExtra("author", author);
                intent.putExtra("content", content);
                intent.putExtra("datetime", textDate.getText());
                */
                v.getContext().startActivity(intent);
            }
        });
    }
```
