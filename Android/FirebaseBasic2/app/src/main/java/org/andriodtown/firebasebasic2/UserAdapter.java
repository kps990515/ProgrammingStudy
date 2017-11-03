package org.andriodtown.firebasebasic2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-31.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {

    Callback callback;
    List<User> data = new ArrayList<>();

    public UserAdapter(Callback callback){
        this.callback = callback;
    }

    public void setDataAndRefresh(List<User> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public UserAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.Holder holder, int position) {
        User user = data.get(position);
        holder.txt_id.setText(user.email);
        holder.txt_token.setText(user.token);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView txt_id;
        TextView txt_token;
        public Holder(View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_token = itemView.findViewById(R.id.txt_token);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = txt_id.getText().toString();
                    String token = txt_token.getText().toString();
                    callback.setIdAndToken(id,token);
                }
            });

        }
    }

    public interface Callback{
        public void setIdAndToken(String id, String token);
    }
}
