# CustomAdapter
- RecyclerView를 상속받고 <Holder>를 붙여서 자동생성

### CustomAdapter부분

```java
//<>붙이면 holer붙인게 자동정의 되서 편하다
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{
    // 1. 데이터 저장소
    private List<PicNote> data;

    public void setData(List<PicNote> data){
        this.data = data;
    }

    // 2. 개수
    @Override
    public int getItemCount() {
        return data.size();
    }
    // 3. 홀더 생성
    // 목록에서 아이템이 최초 호출되면 View Holder를 생성해준다
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 1. 만들어둔 layout 파일을 inflate 한다
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        // 2. inflate 된 View를 Holder의 생성자에 담는다
        Holder holder = new Holder(view);
        // 3. 생성된 holder 리턴
        return holder;
    }
```

### Holder 부분

#### onBindViewHolder()
- picNote를 객체단위로 꺼내서 데이터에 하나씩 입력한다
- picNote의 이름을 가져와 이름을 설정
- picNote의 그림을 가져와 filename을 설정

```java
    // 생성된 View Holder를 RecyclerView에 넘겨서 그리게 한다
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 1. 데이터저장소에 객체단위로 꺼내둔다
        PicNote picNote = data.get(position);
        // 2. 홀더에 있는 위젯값에 값을 입력
        holder.setTitle(picNote.getTitle());
        holder.setFilename(picNote.getBitmap());
    }
```

#### Holder클래스
- itemView중에 하나를 클릭하면 title & filename을 intent에 담는다
- intent에 담아서 [DetailActivity](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemoORM/app/src/androidTest/java/org/andriodtown/androidmemoorm)로 보낸다

```java
    public class Holder extends RecyclerView.ViewHolder{
        private String filename;
        private TextView textTitle;
        public Holder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            setClickListener();
        }
        public void setTitle(String title){
            textTitle.setText(title);
        }
        public void setFilename(String filename){
            this.filename = filename;
        }
        public void setClickListener(){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DetailActivity.class);
                    intent.putExtra("title",textTitle.getText());
                    intent.putExtra("filename",filename);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
```
