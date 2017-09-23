package org.andriodtown.androidmemoorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.andriodtown.androidmemoorm.model.PicNote;

import java.sql.SQLException;

/**
 * Created by user on 2017-09-22.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "ormlite.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // PicNote.class를 참조해서 테이블을 생성
        try {
            TableUtils.createTable(connectionSource, PicNote.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
