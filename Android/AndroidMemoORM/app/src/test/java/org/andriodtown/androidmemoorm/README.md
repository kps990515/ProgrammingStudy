# FileUtil

### 선언파트
- external 저장소를 사용하기 위해 DIR,ROOT 선언

```java
public class FileUtil {

    // 일반적으로는 파일을 저장할 경로는 설정파일에 입력해둔다.
    private static final String DIR = "/temp/picnote";
    private static final String Root = Environment.getExternalStorageDirectory().getAbsolutePath();

### read()
- 파일 경로를 찾아서 없으면 경로 생성
- 있으면 파일을 읽어서 bitmap으로 전환
- bitmap을 DetailActivity으로 반환

```java
    public static Bitmap read(Context context, String filename) throws IOException{
        Bitmap bitmap = null;
        FileInputStream fis = null;

        try {
            // 1. 파일 저장을 위한 디렉토리를 정한다
            // 2. 체크해서 없으면 생성
            File file = new File(Root+"/"+DIR+"/"+filename);
            if(!file.exists()){
                file.mkdirs();
            }
            // 3. 해당 디렉토리에 파일 가져오기
            fis = new FileInputStream(file);
            // 스트림을 bitmap으로 전환
            bitmap = BitmapFactory.decodeStream(fis);
            } catch(IOException e){
            throw e;
        } finally{
            if(fis!=null){
                try{
                    fis.close();
                }catch (IOException e){
                    throw e;
                }
            }
        }

        return bitmap;
    }
```

### write()
- 디렉토리를 검사해서 없으면 생성
- 파일을 검사해서 없으면 생성
- bitmap을 byte배열로 변환해서 파일에 쓰기

    public static void write(Context context, String filename, Bitmap content) throws IOException {
        FileOutputStream fos = null;
        try {
            // 1. 파일 저장을 위한 디렉토리를 정한다
            // 2. 체크해서 없으면 생성
            // 디렉토리 검사
            File dir = new File(Root+"/"+DIR);
            if(!dir.exists()){
                dir.mkdirs();
            }
            // 3. 해당 디렉토리에 파일 쓰기
            // 파일 있는지 검사
            File file = new File(Root+"/"+DIR+"/"+filename);
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            fos.write(bitmapToByteArray(content));
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

### bitmapToByteArray()
- bitmap을 byteArray로 변환

```java
    private static byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
```
