# CustomAdapter

###  선언부분
- 편의를 위해 RecyclerView에 Adapter를 붙이고
- Holder를 <> 안에 넣어준다

```java
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{

    Context context;
    List<String>data;
    ListFragment.Callback callback;
```

### 생성자 부분
- context, data, ListFragment에서 Callback interface도 받는다

```java
    public CustomAdapter(Context context, ListFragment.Callback callback, List<String> data){
        this.context = context;
        this.data = data;
        this.callback = callback;
    }
```

### Holder를 View에 붙이는 파트

```java
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view,callback);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
```

### Holder Class
- ViewHolder를 클릭하면 callback(Main에서 구현)에서 goDetail()를 호출한다
- 이 경우 textView의 값이 DetailFragment로 넘어가게 된다

```java
    class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        public Holder(View itemView, final ListFragment.Callback callback){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.goDetail(textView.getText().toString());
                }
            });
        }
        public void setText(String text){
            textView.setText(text);
        }
    }
}
```
