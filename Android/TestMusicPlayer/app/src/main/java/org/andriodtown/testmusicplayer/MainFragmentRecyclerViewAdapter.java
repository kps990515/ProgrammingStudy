package org.andriodtown.testmusicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.andriodtown.testmusicplayer.MainListFragment.OnListFragmentInteractionListener;
import org.andriodtown.testmusicplayer.model.Music;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MainFragmentRecyclerViewAdapter extends RecyclerView.Adapter<MainFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<Music.Item> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MainFragmentRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
        mValues = mListener.getList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).title);
        holder.mAlbumView.setImageURI(mValues.get(position).albumUri);
        holder.mArtistView.setText(mValues.get(position).artist);
        holder.position = position;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.openPlayer(holder.position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int position;
        public final View mView;
        public final TextView mContentView;
        public final ImageView mAlbumView;
        public final TextView mArtistView;
        public Music.Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.txt_title);
            mAlbumView = view.findViewById(R.id.album);
            mArtistView = view.findViewById(R.id.txt_artist);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
