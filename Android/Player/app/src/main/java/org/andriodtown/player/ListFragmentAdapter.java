package org.andriodtown.player;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.andriodtown.player.domain.Music;

import java.util.List;

/**
 * Created by pc on 10/12/2017.
 */

public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.Holder>{

    ListFragment.IActivityInteract listener;
    List<Music.Item> data;

    public ListFragmentAdapter(List<Music.Item> data, ListFragment.IActivityInteract listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Music.Item item = data.get(position);
        holder.position = position;
        holder.titleView.setText(item.title);
        holder.albumView.setImageURI(item.albumUri);
        holder.artistView.setText(item.artist);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        int position;
        TextView titleView;
        TextView artistView;
        ImageView albumView;
        public Holder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.txt_title);
            albumView = itemView.findViewById(R.id.album);
            artistView = itemView.findViewById(R.id.txt_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.openPlayer(position);
                }
            });
        }
    }
}
