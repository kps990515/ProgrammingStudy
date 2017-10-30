package org.andriodtown.firebasebasic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-30.
 */

public class BbsAdapter extends RecyclerView.Adapter<BbsAdapter.Holder> {

    List<Bbs> data = new ArrayList<>();

    public BbsAdapter(){

    }

    @Override
    public BbsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new BbsAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Bbs datas = data.get(position);
        holder.txt_id.setText(datas.id);
        holder.txt_title.setText(datas.title);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void refresh(List<Bbs> data){
        this.data = data;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView txt_id;
        TextView txt_title;

        public Holder(View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_title = itemView.findViewById(R.id.txt_name);
        }
    }
}
