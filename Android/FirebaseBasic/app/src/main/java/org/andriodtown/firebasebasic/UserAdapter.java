package org.andriodtown.firebasebasic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-30.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder>{

    List<User> data = new ArrayList<>();
    Callback callback;
    public UserAdapter(Callback callback){
        this.callback = callback;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        User datas = data.get(position);
        holder.txt_id.setText(datas.user_id);
        holder.txt_name.setText(datas.username);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void refresh(List<User> data){
        this.data = data;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView txt_id;
        TextView txt_name;

        public Holder(View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_name = itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = txt_id.getText().toString();
                    callback.setUserid(userid);
                    Toast.makeText(v.getContext(), "userid="+userid,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public interface Callback{
        public void setUserid(String userid);
    }
}
