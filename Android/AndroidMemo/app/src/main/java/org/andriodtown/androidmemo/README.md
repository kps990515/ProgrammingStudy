# Memo.java

### 설명
 1. 그냥 Memo()하면 변수들만 사용가능하게
 2. Memo(String)호출하면
 3. String 줄 단위 & 내용 별 분리해서 Memo에 저장

### Memo()파트
- 파라미터에 따라 호출내용이 달라진다
- 파라미터 없으면 호출함수X
- String이면 parse()호출
- 줄 단위 분해 -> DELIMETER로 내용 따라 구분
- key가 있으면 key&value저장 / 없으면 content로 간주해 value만 저장

```java
public Memo(){

    }

    public Memo(String text){
        parse(text);
    }

    public void parse(String text){
        // 문자열을 줄 단위로 분해
        String lines[] = text.split("\n");
        // 문자열을 행(DELIMETER)단위로 분해
        for(String line : lines){
            String columns[] = line.split(DELIMETER);
            String key = "";
            String value = "";
            if(columns.length==2){
                key = columns[0];
                value = columns[1];
            }else{
                key="";
                value=columns[0];
            }
            switch (key){
                case "no":
                    setNo(Integer.parseInt(value));
                    break;
                case "title":
                    setTitle(value);
                    break;
                case "author":
                    setAuthor(value);
                    break;
                case "datetime":
                    setDatetime(Long.parseLong(value));
                    break;
                case "content":
                    setContent(value);
                    break;
                default:
                    appendContent(value);
            }
        }
    }
```

### toString()파트
- String을 메모로 받아올 때 값을 입력하기 쉽도록
- key + DELIMETER + value + \n 형식지정

```java
public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("no").append(DELIMETER).append(no).append("\n");
        result.append("title").append(DELIMETER).append(title).append("\n");
        result.append("author").append(DELIMETER).append(author).append("\n");
        result.append("datetime").append(DELIMETER).append(datetime+"").append("\n");
        result.append("content").append(DELIMETER).append(content).append("\n");

        return result.toString();
    }
```

### 그 외 파트

```java
public class Memo {

    // 이름,제목,날짜 구분하는 구분자
    private static final String DELIMETER = "//";

    private int no;
    private String title;
    private String author;
    private String content;
    private long datetime;

    }
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public void appendContent(String value){
        this.content+="\n" +value;
    }
```
