# Gallery & Camera

### [MainActivity](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/Gallery/app)로 이동

### Manifest - 추가해야 되는 코드 위주
1. Permission
```java
<uses-permission android:name="android.permission.CAMERA"></uses-permission>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
```

2. Provider

  사진을 저장하기 위해 파일에 대한 권한 획득 설정  
  authorities에 그냥 어플 id를 써도 되지만 이렇게 쓰면 gradle에서 바로 불러온다
  file_path에서 경로를 불러온다

```java
<provider
    android:authorities="${applicationId}.provider"
    android:name="android.support.v4.content.FileProvider"
    android:exported="false"
    android:grantUriPermissions="true">
    <!-- Provider가 사용하는 파일의 경로 -->
    <meta-data
    android:name="android.support.FILE_PROVIDER_PATHS"
    android:resource="@xml/file_path"
        >
    </meta-data>
</provider>
```

### file_path.xml
- meta-data에 파일 경로를 정의하는 역활

```java
<?xml version="1.0" encoding="utf-8"?>
<paths>
    //name(논리구조) : uri 주소체계의 prefix -> content://Camera. -->
    // path(실제 디스크 경로) : / 외부저장소의 루트 / CameraN 디렉토리를 사용
    <external-path name = "Camera" path="CameraN">

    </external-path>
</paths>
```
