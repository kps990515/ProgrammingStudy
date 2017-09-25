# DBHelper
- picNote를 사용해서 테이블 생성

```java
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
```

# PicNote
- @DatabaseTable을 통해 테이블 이름 설정
- @DatabaseField를 통해 테이블에 들어갈 필드선별

```java
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
```
