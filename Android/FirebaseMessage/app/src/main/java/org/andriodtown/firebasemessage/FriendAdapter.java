package org.andriodtown.firebasemessage;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.andriodtown.firebasemessage.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-11-06.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.Holder> {

    private List<User> data = new ArrayList<>();

    public void setDataAndRefresh(List<User> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public FriendAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(FriendAdapter.Holder holder, int position) {
        User friend = data.get(position);
        holder.friend = friend;
        holder.txt_name.setText(friend.name);
        holder.txt_email.setText(friend.email);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        User friend;
        TextView txt_name;
        TextView txt_email;
        public Holder(View itemView) {
            super(itemView);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_name = itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ChatActivity.class);
                    intent.putExtra("friend_id",friend.id);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
