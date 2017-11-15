package org.andriodtown.rxbasic03_subject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;

    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new CustomAdapter();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    PublishSubject<String> publishSubject = PublishSubject.create();

    // publichSubject
    // 구독 시점부터 데이터를 읽을 수 있다 > 이전에 발행된 데이터는 읽을 수 없다
    public void doPublish(View view) {
        // publishSubject 발행
        new Thread(
                () ->
                {
                    for (int i = 0; i < 10; i++) {
                        publishSubject.onNext("SOMETHING..." + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
    public void doGetPublish(View view){
        // publishSubject에서 값 가져와서 세팅
        data.clear();
        publishSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                str -> {
                    data.add(str);
                    adapter.setDataAndRefresh(data);
                }
        );
    }
    //BehaviorSubject
    // 구독 이전에 발행된 마지막 아이템부터 구독할 수 있다
    BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
    public void doBehavior(View view){
        new Thread(
                () ->
                {
                    for (int i = 0; i < 10; i++) {
                        behaviorSubject.onNext("SOMETHING..." + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
    public void doGetBehavior(View view){
        data.clear();
        behaviorSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        str -> {
                            data.add(str);
                            adapter.setDataAndRefresh(data);
                        }
                );
    }

    // ReplaySubject
    // 발행된 아이템을 모두 구독할 수 있다
    ReplaySubject<String> replaySubject = ReplaySubject.create();
    public void doReplay(View view){
        new Thread(
                () ->
                {
                    for (int i = 0; i < 10; i++) {
                        replaySubject.onNext("SOMETHING..." + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
    public void doGetReplay(View view){
        data.clear();
        replaySubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        str -> {
                            data.add(str);
                            adapter.setDataAndRefresh(data);
                        }
                );
    }

    // AsyncSubject
    // 발행이 완료되면 마지막 발행 아이템만 가져온다
    AsyncSubject<String> asyncSubject = AsyncSubject.create();
    public void doAsync(View view){
        new Thread(
                () ->
                {
                    for (int i = 0; i < 10; i++) {
                        asyncSubject.onNext("SOMETHING..." + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    asyncSubject.onComplete();
                }
        ).start();
    }
    public void doGetAsync(View view){
        data.clear();
        asyncSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        str -> {
                            data.add(str);
                            adapter.setDataAndRefresh(data);
                        }
                );
    }


}
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{
    List<String> data = new ArrayList<>();
    public void setDataAndRefresh(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,parent, false);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.text1.setText(data.get(position));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class Holder extends RecyclerView.ViewHolder{
        TextView text1;
        public Holder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }
    }
}