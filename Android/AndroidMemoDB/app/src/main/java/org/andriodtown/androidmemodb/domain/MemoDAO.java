package org.andriodtown.androidmemodb.domain;

/*
DAO : data access object
데이터 조작을 담당

예)
MemoDAO dao = new DAO()
String query = "insert into..."
dao.create(query)
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.andriodtown.androidmemodb.DBHelper;

import java.util.ArrayList;

public class MemoDAO {

    DBHelper helper;

    public MemoDAO(Context context){
        helper = new DBHelper(context);
    }

    // 공통사항
    // 1. 데이터베이스에 연결
    // 2. 조작
    // 3. 연결을 해제

    // 생성 C
    public void create(String query){
        SQLiteDatabase con = helper.getWritableDatabase();
        con.execSQL(query);
        con.close();
    }
    // 읽기 R
    public ArrayList<Memo> read(){

        String query = "select id, title, content, n_date from memo";

        ArrayList<Memo> data = new ArrayList<>();

        SQLiteDatabase con = helper.getReadableDatabase();
        Cursor cursor = con.rawQuery(query,null);

        while(cursor.moveToNext()) {
            // 컬럼 인덱스를 가져오는 명령어
            Memo memo = new Memo();
            for(int i=0; i<cursor.getColumnCount(); i++)
            {
                switch(cursor.getColumnName(i)){
                    case "id":
                        memo.id=cursor.getInt(i);
                        break;
                    case "title":
                        memo.title=cursor.getString(i);
                        break;
                    case "content":
                        memo.content=cursor.getString(i);
                        break;
                    case "n_date":
                        memo.n_date=cursor.getString(i);
                        break;
                }
            }
            data.add(memo);
        }
        con.close();
        return data;
    }
    // 수정 U
    public void update(String query){
        SQLiteDatabase con = helper.getWritableDatabase();
        con.execSQL(query);
        con.close();
    }
    // 삭제 D
    public void delete(String query){
        SQLiteDatabase con = helper.getWritableDatabase();
        con.execSQL(query);
        con.close();
    }

    public void close(){
        helper.close();
    }
}
