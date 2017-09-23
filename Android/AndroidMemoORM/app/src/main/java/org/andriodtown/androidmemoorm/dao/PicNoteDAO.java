package org.andriodtown.androidmemoorm.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import org.andriodtown.androidmemoorm.DBHelper;
import org.andriodtown.androidmemoorm.model.PicNote;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2017-09-22.
 */

public class PicNoteDAO {

    DBHelper helper;
    Dao<PicNote, Long> dao =  null;

    // 솔직히 이렇게 하면 dao를 따로 만들필요는 없다
    // 하지만 독립성을 위해서 만든다
   public PicNoteDAO(Context context){
        helper = new DBHelper(context);
        try {
            dao =  helper.getDao(PicNote.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(PicNote picnote){
        try {
            dao.create(picnote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PicNote> readAll(/*쿼리*/){
        List<PicNote> result = null;
        try {
            result = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public PicNote readOneById(long id){
        PicNote result = null;
        try {
            result = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<PicNote> search(String word){ // 그림
        // format을 쓴 이유는 가독성을 위해
        // C의 printf같은 느낌
        String query = String.format("select * from picnote where title like '%% %s %%'",word);
                                   // select * from picnote where title like '%그림%
        List<PicNote> result = null;
        try {
            GenericRawResults<PicNote> temp = dao.queryRaw(query,dao.getRawRowMapper());
            result = temp.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(PicNote picNote){
        try {
            dao.update(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // picnote말고 id값을 받아와도 되지만
    // 통일성 위해 picnote받아오는 걸로
    public void delete(PicNote picNote){
        try {
            dao.delete(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
