package org.andriodtown.remotbbs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.andriodtown.remotbbs.model.Data;

/**
 * Created by user on 2017-10-26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    Data datas[];

    public RecyclerAdapter(Data[] datas){
        this.datas = datas;
    }

    @Override
    public RecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.Holder holder, int position) {
        Data data = datas[position];
        holder.txt_no.setText(data.getNo());
        holder.txt_title.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    public void addDataAndRefresh(Data [] data) {
        // 기존 데이터에 신규데이터 합치기
        Data [] temp = new Data[datas.length + data.length];
        System.arraycopy(datas,0,temp,0,datas.length);
        System.arraycopy(data,0,temp,datas.length,data.length);
        datas = temp;
        // 리스트 갱신
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView txt_no;
        TextView txt_title;

        public Holder(View itemView) {
            super(itemView);
            txt_no = itemView.findViewById(R.id.txt_no);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }
}
