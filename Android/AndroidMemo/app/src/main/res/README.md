# FileUtil.java

### 설명
 1. 그냥 Memo()하면 변수들만 사용가능하게
 2. Memo(String)호출하면
 3. String 줄 단위 & 내용 별 분리해서 Memo에 저장

### read()파트
- String을 담을 StringBuilder생성
- filename을 받으면 스트림에 넣고
- 스트림에 버퍼를 달고 -> 버퍼의 크기 지정
- 다 읽거나 버퍼 차면 sb로 append
- 다 끝나면 fis,bis close() & sb 반환

```java
public static String read(Context context, String filename) throws IOException{
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
             fis = context.openFileInput(filename);
            //버프를 달고
             bis = new BufferedInputStream(fis);
            // 한번에 읽어올 버퍼양을 설정
            byte buffer[] = new byte[1024];
            // 현재 읽은양을 담는 변수설정
            int count = 0;
            // bis.read(buffer)는 설정 값 1024까지 읽어준다
            // 꽉 차면 -1 return
            while ((count = bis.read(buffer)) != -1) {
                String data = new String(buffer, 0, count);
                sb.append(data);
            }
        }catch(IOException e){
            throw e;
        } finally{
            if(bis!=null){
                try{
                    bis.close();
                }catch (IOException e){
                    throw e;
                }
            }
            if(fis!=null){
                try{
                    fis.close();
                }catch (IOException e){
                    throw e;
                }
            }
        }

        return sb.toString();
    }
```

### write()파트
- output스트림으로 content를 MODE_PRIVATE으로 저장

```java
public static void write(Context context, String filename, String content) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(filename,MODE_PRIVATE);
            fos.write(content.getBytes());
        }catch(Exception e){
            throw e;
        }finally{
            if(fos!=null){
                try{
                    fos.close();
                }catch(IOException e){
                    throw e;
                }
            }
        }
    }
```
