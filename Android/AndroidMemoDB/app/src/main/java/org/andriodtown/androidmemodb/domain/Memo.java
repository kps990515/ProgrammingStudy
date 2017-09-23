package org.andriodtown.androidmemodb.domain;

/**
 * Created by user on 2017-09-21.
 */

public class Memo {
    int id;
    String title;
    String content;
    String n_date;

    public Memo(){

    }

    public Memo(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return id+"|"+title+"|"+content+"|"+n_date+"\n";
    }

}
