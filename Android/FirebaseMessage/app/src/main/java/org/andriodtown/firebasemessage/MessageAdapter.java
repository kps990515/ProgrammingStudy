package org.andriodtown.firebasemessage;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.andriodtown.firebasemessage.model.Msg;
import org.andriodtown.firebasemessage.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-11-06.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.Holder> {

    private List<Msg> data = new ArrayList<>();
    Context context;

    public MessageAdapter(Context context){
        this.context = context;
    }

    public void setDataAndRefresh(List<Msg> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MessageAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.Holder holder, int position) {
        Msg msg = data.get(position);
        holder.msg = msg;
        holder.txt_friendname.setText(msg.user_id);
        holder.txt_msg.setText(msg.msg);
        // 메세지 작성 id가 내 id와 같으면 메시지 레이아웃을 오른쪽 정렬
        if(msg.user_id.equals(PreferenceUtil.getUserId(context))){
            holder.itemLayout.setGravity(Gravity.RIGHT);
        }else{
            holder.itemLayout.setGravity(Gravity.LEFT);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        Msg msg;
        LinearLayout itemLayout;
        TextView txt_friendname;
        TextView txt_msg;
        CardView cardView;
        public Holder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.itemLayout);
            txt_friendname = itemView.findViewById(R.id.txt_friendname);
            txt_msg = itemView.findViewById(R.id.txt_msg);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
