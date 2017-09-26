package org.andriodtown.customgallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2017-09-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder>{
    Context context;
    List<String> data;

    public GalleryAdapter(Context context){
        this.context = context;
    }

    public void setData(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String path = data.get(position);
        Uri uri = Uri.parse(path);
        holder.setImageUri(uri);
    }

    class Holder extends RecyclerView.ViewHolder{
        private Uri uri;
        private ImageView imageView;
        private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_item);
            textView = (TextView)itemView.findViewById(R.id.txt_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("imagePath", uri.getPath());
                    Activity activity = (Activity) context;
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();

                }
            });
        }
        public void setImageUri(Uri uri){
            imageView.setImageURI(uri);
            this.uri = uri;
        }
        public void setTextView(String date){
            textView.setText(date);
        }
    }
}
