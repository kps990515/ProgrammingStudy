package org.andriodtown.fragmentbasic;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
        // 여기는 코드를 작성하면 안됨
    }

    @Override
    public void onAttach(Context context) { // 이 context가 나를 삽입한 액티비티(여기서는Main)
        Log.d("Fragment","==========OnAttach()");
        // 1. 나를 사용한 액티비티가 내가 제공한 인터페이스를 구현했는지 확인
        if(context instanceof Callback){ // Main은 Callback을 이식함
            callback = (Callback) context; // 2. 구현했다면 인터페이스로 캐스팅해서 사용(callback = Main)
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.d("Fragment","==========OnDetach()");
        super.onDetach();
    }

    Callback callback = null;
    Button goDetail;
    // 얘는 액티비티에 부착되면서 동작시작
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        goDetail = (Button)view.findViewById(R.id.btn_detail);
        goDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.goDetail(); // 내가 설계해 놓은 인터페이스를 호출
                                     // 나를 사용하는 측은 callback interface를 구현해야만 한다
            }
        });

        return view;
    }

    public interface Callback{
        public void goDetail();
    }

    @Override
    public void onStart() {
        Log.d("Fragment","==========OnStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Fragment","==========OnResume()");
        super.onResume();
    }

}
