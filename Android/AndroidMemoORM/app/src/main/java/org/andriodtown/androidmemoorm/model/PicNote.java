package org.andriodtown.androidmemoorm.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 데이터 모델링 - 도메인 추출 - 개념 모델링
 */

//테이블 잉름
@DatabaseTable(tableName = "picnote") // 테이블이름도 필드처럼 소문자로 맞춰주려고
public class PicNote {
    // 식별자 - 이것은 필드에 넣겠다
    @DatabaseField(generatedId = true) // 자동증가
    private long id;
    // 제목 - 이것도
    @DatabaseField
    private String title;
    // 그림
    @DatabaseField
    private String bitmap_path;
    // 날짜
    private long datetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBitmap() {
        return bitmap_path;
    }

    public void setBitmap(String bitmap_path) {
        this.bitmap_path = bitmap_path;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
