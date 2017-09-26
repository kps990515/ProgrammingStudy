package org.andriodtown.gallery;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends BaseActivity {

    ImageView pictureView;

    @Override
    public void init(){
        setContentView(R.layout.activity_main);
        pictureView = (ImageView)findViewById(R.id.pictureView);
    }

    // 저장된 파일의 경로를 가지는 컨텐츠 uri
    Uri fileUri = null;

    public void onCamera(View view){
        // 카메라 앱 띄워서 결과 이미지 저장하기
        // 1. Intent 만들기
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 2. 호환성 처리(버전체크)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            // 3. 이미지가 저장되는 파일 객체(빈 파일 생성)
            // 3.1 이미지를 저장하는 곳에 권한이 부여되어 있어야 한다
            //  롤리팝 부터는 File Provider를 선언해줘야 한다 -> Manifest에
            try {
                File cameraFile = createFile();
                // 갤러리에서 사진이 안나올 때
                refreshMedia(cameraFile);

                fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider", cameraFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent,REQ_CAMERA);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            startActivityForResult(intent, REQ_CAMERA);
        }
    }

    // 갤러리 파일 갱신
    private void refreshMedia(File file){
        MediaScannerConnection.scanFile(
                this,
                new String[] { file.getAbsolutePath() },
                null,
                new MediaScannerConnection.OnScanCompletedListener(){
                    public void onScanCompleted(String path, Uri uri){

                    }
                }
        );
    }

    // 이미지를 저장하기 위해 쓰기 권한이 있는 빈 파일을 생성해두는 함수
    // throws IOException을 통해 이 함수를 불러오는 곳에서 try/catch 시킨다
    // 왜냐하면 이 함수를 쓰는 곳은 많을 수 있는데 어디서 에러가 난건지 파악하기 위해서
    private File createFile() throws IOException {
        // 임시 파일명 생성
        String tempFileName = "Temp_"+System.currentTimeMillis();
        // 임시파일 저장용 디렉토리 생성
        File tempDir = new File(Environment.getExternalStorageDirectory() + "/CameraN/");
        // 생성 체크
        if(!tempDir.exists()){
            tempDir.mkdirs();
        }
        // 실제 임시파일을 생성
        // 파일명 + 확장자 + 디렉토리
        File tempFile = File.createTempFile(tempFileName, ".jpg", tempDir);
        return tempFile;
    }


    private static final int REQ_CAMERA = 222;
    private static final int REQ_GALLERY = 333;

    public void onGallery(View view){
        // 인텐트를 사용해서 갤러리 액티비티 호출
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQ_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 결과값이 있을 때만 실행(사진 안누르고 뒤로가기시 에러안뜨게)
        if(resultCode == RESULT_OK) {
            Uri imageUri = null;
            switch (requestCode) {
                // 갤러리 액티비티 종료시 호출
                case REQ_GALLERY:
                    if(data != null) {
                        // data에 값이 있으면 갤러리에서 선택한 data를 가져온다
                        imageUri = data.getData();
                        pictureView.setImageURI(imageUri);
                    }
                    break;
                case REQ_CAMERA:
                    // 버전체크
                    if(data != null) {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                            imageUri = data.getData();
                        }else{
                            imageUri = fileUri;
                        }
                    }
                    pictureView.setImageURI(imageUri);
                    break;
            }
        }
    }
}
