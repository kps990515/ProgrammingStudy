# ListAdapter
- Glide를 사용해 이미지 Load & 설정

```java
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    List<User> data;

    public ListAdapter(Context context, List<User> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        User user = data.get(position);
        holder.txt_id.setText(user.getId()+"");
        holder.txt_login.setText(user.getLogin());
        // 이미지 불러오기
        Glide.with(context).load(user.getAvatar_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txt_id;
        TextView txt_login;
        View mView;
        User userdata;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            imageView = itemView.findViewById(R.id.imageView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_login = itemView.findViewById(R.id.txt_login);

        }
    }
}
```
