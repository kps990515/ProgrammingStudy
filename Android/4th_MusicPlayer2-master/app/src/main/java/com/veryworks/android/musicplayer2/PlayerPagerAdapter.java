package com.veryworks.android.musicplayer2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.veryworks.android.musicplayer2.domain.Music;

import java.util.List;

/**
 * Created by pc on 10/11/2017.
 */

public class PlayerPagerAdapter extends PagerAdapter {
    Context context;
    List<Music.Item> data;

    public PlayerPagerAdapter(Context context, List<Music.Item> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Music.Item item = data.get(position);

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_player, null);
        TextView  textTitle  = view.findViewById(R.id.textTitle);
        textTitle.setText(item.title);
        TextView  textArtist = view.findViewById(R.id.textArtist);
        textArtist.setText(item.artist);
        ImageView imgAlbum   = view.findViewById(R.id.imgAlbum);
        imgAlbum.setImageURI(item.albumUri);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

}
