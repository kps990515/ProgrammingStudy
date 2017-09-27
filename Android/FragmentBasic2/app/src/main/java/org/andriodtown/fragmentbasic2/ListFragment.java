package org.andriodtown.fragmentbasic2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }

    Context context;
    Callback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if(context instanceof Callback){
            callback = (Callback)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        init(view);
        return view;
    }


    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    public void init(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // 데이터 임의생성 후 넘겨준다
        List<String>data = new ArrayList<>();
        for(int i=0; i<100; i++){
            data.add("tempData"+i);
        }
        customAdapter = new CustomAdapter(context,callback, data);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public interface Callback{
        public void goDetail(String value);
    }
}
