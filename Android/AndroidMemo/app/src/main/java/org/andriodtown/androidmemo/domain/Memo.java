package org.andriodtown.androidmemo.domain;

/**
 * Created by user on 2017-09-19.
 */

public class Memo {

    private static final String DELIMETER = "//";
    private int no;
    private String title;
    private String author;
    private String content;
    private long datetime;

    public Memo(String text){
        // 문자열을 줄 단위로 분해
        String lines[] = text.split("");
        // 문자열을 행(":^:")단위로 분해
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
                    break;
            }
        }
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

    // memo가져올 때 보기 편하려고
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("no").append(DELIMETER).append(no).append("\n");
        result.append("title").append(DELIMETER).append(title).append("\n");
        result.append("author").append(DELIMETER).append(author).append("\n");
        result.append("datetime").append(DELIMETER).append(datetime+"").append("\n");
        result.append("content").append(DELIMETER).append(content).append("\n");

        return result.toString();
    }
    // 쓰고 저장할때 Byte[]형식이라
    public byte[] toBytes(){
        return toString().getBytes();
    }

    public void appendContent(String value){
        this.content+=content;
    }
}
