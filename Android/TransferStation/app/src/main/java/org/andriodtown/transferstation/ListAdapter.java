package org.andriodtown.transferstation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.andriodtown.transferstation.model.Row;

import java.util.List;

/**
 * Created by user on 2017-10-17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    List<Row> data;

    public ListAdapter(Context context, List<Row> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Row row = data.get(position);
        holder.txt_num.setText(row.getSN());
        holder.txt_station.setText(row.getSTATN_NM());
        holder.txt_week.setText(row.getWKDAY());
        holder.txt_sat.setText(row.getSATDAY());
        holder.txt_sun.setText(row.getSUNDAY());
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_num;
        TextView txt_station;
        TextView txt_Week;
        TextView txt_week;
        TextView txt_Sun;
        TextView txt_sun;
        TextView txt_Sat;
        TextView txt_sat;

        public ViewHolder(View view) {
            super(view);
            txt_num = view.findViewById(R.id.txt_num);
            txt_station = view.findViewById(R.id.txt_station);
            txt_Week = view.findViewById(R.id.txt_Week);
            txt_week = view.findViewById(R.id.txt_week);
            txt_Sun = view.findViewById(R.id.txt_Sun);
            txt_sun = view.findViewById(R.id.txt_sun);
            txt_Sat = view.findViewById(R.id.txt_Sat);
            txt_sat = view.findViewById(R.id.txt_sat);

        }
    }
}
