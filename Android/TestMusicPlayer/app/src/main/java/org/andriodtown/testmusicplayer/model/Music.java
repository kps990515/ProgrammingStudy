package org.andriodtown.testmusicplayer.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-11.
 */

public class Music {
    private static Music music = null;
    public static List <Item> musiclist = new ArrayList<>();

    private Music(){

    }

    public static Music getMusic(){
        if(music == null){
            music = new Music();
        }
        return music;
    }

    public void load(Context context){
        ContentResolver contentResolver = context.getContentResolver();

        Uri mediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String proj[] = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = contentResolver.query(mediaUri,proj,null,null,proj[2] + " ASC");
        if(cursor!=null){
            while(cursor.moveToNext()){
                Item item = new Item();
                item.id = getValue(cursor, proj[0]);
                item.albumId = getValue(cursor, proj[1]);
                item.title = getValue(cursor, proj[2]);
                item.artist = getValue(cursor, proj[3]);

                item.musicUri = makeMusicUri(item.id);
                item.setAlbumUri(makeAlbumUri(item.albumId));
                musiclist.add(item);
            }
            cursor.close();
        }
    }

    private String getValue(Cursor cursor, String s){
        int index = cursor.getColumnIndex(s);
        return cursor.getString(index);
    }

    private Uri makeMusicUri(String musicId){
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(contentUri, musicId);
    }

    private Uri makeAlbumUri(String albumId){
        String contentUri = "content://media/external/audio/albumart/";
        return Uri.parse(contentUri + albumId);
    }

    public class Item{
        public String id; // 음악ID
        public String albumId; // 앨범ID
        public String artist;
        public String title;

        public Uri musicUri; //음악 uri
        public Uri albumUri; //앨범이미지 uri

        public Uri getAlbumUri() {
            return albumUri;
        }
        public void setAlbumUri(Uri albumUri) {
            this.albumUri = albumUri;
        }
    }
}
