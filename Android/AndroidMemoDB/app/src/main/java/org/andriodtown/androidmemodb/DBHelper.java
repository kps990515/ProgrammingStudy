package org.andriodtown.androidmemodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2017-09-21.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sqlite.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context){
        // factory는 회사당 connection으 새로 만들어 병목현상 방지
        // factory가 null이 된 이유는 그 만큼 data 교환이 안 일어나서...
        super(context, DB_NAME, null, DB_VERSION);
        // super에서 넘겨받은 DB가 생성되어 있는지 확인 후
        // 1. 없으면 onCreate() 호출
        // 2. 있으면 버전을 체크
        // 2-1. 생성되어 있는 DB보다 버전이 높으면 onUpgrade() 호출
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 최초 생성할 Table을 정의
        String createQuery = "CREATE TABLE `memo` ( \n" +
                             "`id` INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                             "`title` TEXT, \n" +
                             "`content` TEXT, \n" +
                             "`n_date` TEXT )";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
