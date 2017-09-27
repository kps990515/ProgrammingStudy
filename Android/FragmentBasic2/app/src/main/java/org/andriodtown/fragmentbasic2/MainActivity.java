package org.andriodtown.fragmentbasic2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 코드 검색해보기
        if(savedInstanceState!=null){
            return;
        }
        init();
    }
        public void init() {
        // 화면 세로 가로 체크
            if (getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_PORTRAIT) {
                     initFragment();
            }
        }

        private void initFragment(){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new org.andriodtown.fragmentbasic2.ListFragment())
                    .commit();
        }

    @Override
    public void goDetail(String value) {
        if(getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT){
            // 디테일 프래그먼트를 화면에 보이면서 값을 전달
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("value", value);
            detailFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,detailFragment)
                    .addToBackStack(null)
                    .commit();
        }else {
            // 현재 레이아웃에 삽입되어 있는 프래그먼트를 가져온다
            DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);
            if(detailFragment != null) {
                detailFragment.setText(value);
            }
        }
    }
}
