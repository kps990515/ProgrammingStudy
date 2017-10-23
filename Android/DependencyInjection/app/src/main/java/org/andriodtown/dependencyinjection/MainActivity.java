package org.andriodtown.dependencyinjection;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@Fullscreen
@EActivity(R.layout.activity_main)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainActivity extends AppCompatActivity {

    @ViewById
    TextView text;

    @AfterViews
    public void init(){
        text.setText("aaaaaa");
    }

    @Background
    public void run(){
        //여기는 subThread에서 실행
        for(int i=0; i<10; i++){
            main(i);
        }
    }

    @MainThread
    public void main(int i){
        // 여기는 Mainthread
        text.setText(i);
    }

}
