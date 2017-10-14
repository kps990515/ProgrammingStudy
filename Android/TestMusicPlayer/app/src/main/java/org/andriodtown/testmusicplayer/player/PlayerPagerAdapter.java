package org.andriodtown.testmusicplayer.player;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.andriodtown.testmusicplayer.R;
import org.andriodtown.testmusicplayer.model.Music;

import java.util.List;

/**
 * Created by user on 2017-10-11.
 */

public class PlayerPagerAdapter extends PagerAdapter {

    Context context;
    List<Music.Item> data;

    public PlayerPagerAdapter(Context context, List<Music.Item> data){
        this.context=context;
        this.data=data;
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

        Music.Item item= data.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.item_player,null);

        TextView txt_title = view.findViewById(R.id.txt_title);
        txt_title.setText(item.title);
        TextView txt_artist = view.findViewById(R.id.txt_artist);
        txt_artist.setText(item.artist);
        ImageView img_album =  view.findViewById(R.id.img_album);
        img_album.setImageURI(item.getAlbumUri());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
