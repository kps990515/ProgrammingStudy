package org.andriodtown.customgallery;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends BaseActivity {
    private static final int REQ_GALLERY = 999;
    ImageView imageView;


    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
    }



    public void onGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivityForResult(intent, REQ_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageUri = null;
            switch (requestCode) {
                // 갤러리 액티비티 종료시 호출
                case REQ_GALLERY:
                    if (data != null) {
                        // data에 값이 있으면 갤러리에서 선택한 data를 가져온다
                        String imagePath = data.getStringExtra("imagePath");
                        imageUri = Uri.parse(imagePath);
                        imageView.setImageURI(imageUri);
                    }
                    break;
            }
        }
    }
}
