package org.andriodtown.subway;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.andriodtown.subway.model.Station.station.Row;

import java.util.List;

/**
 * Created by user on 2017-10-19.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    Context context;
    List<Row>data;

    public ListAdapter(Context context, List<Row>data){
        this.context=context;
        this.data=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_line,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Row row = data.get(position);
        holder.txt_station.setText(row.getSTATION_NM());
        holder.stationC = row.getSTATION_CD();
        holder.stationN = row.getSTATION_NM();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_station;
        String stationC;
        String stationN;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_station = itemView.findViewById(R.id.txt_station);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(),StationActivity.class);
            intent.putExtra("StationCD",stationC);
            intent.putExtra("StationNM",stationN);
            v.getContext().startActivity(intent);
        }
    }
}
