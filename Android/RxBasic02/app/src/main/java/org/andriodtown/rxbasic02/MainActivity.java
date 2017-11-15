package org.andriodtown.rxbasic02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<String> months = new ArrayList<>();
    Observable<String> observable;
    Observable<String> observableZip;

    String monthString[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 1월부터 12월 가져오기
        DateFormatSymbols dfs = new DateFormatSymbols();
        monthString = dfs.getMonths();

        // 1. 발행자
        observable = Observable.create(
                e -> {
                    try {
                        for (String month : monthString) {
                            e.onNext(month);
                            Thread.sleep(1000);
                        }
                        e.onComplete();
                    }catch(Exception ex){
                        throw ex;
                    }
                }
        );

        observableZip = Observable.zip(
          Observable.just("BeWhy", "Curry"),
          Observable.just("Singer", "Bascketball Player"),
          (item1, item2) -> "job=".concat(item2).concat(", name=").concat(item1)
        );
    }
    /*
        map은 데이터 자체를 변형할 수 있다.
    */
    public void doMap(View view){
        months.clear();
        // 2. 구독자
        observable
                .subscribeOn(Schedulers.io())              // 옵저버블의 thread를 지정
                .observeOn(AndroidSchedulers.mainThread()) // 옵저버의 thread를 지정
                .filter(str->str.equals("3월")?false:true)
                .map(str->"["+str+"]")
                .subscribe(
                        str -> {
                            months.add(str);
                            adapter.setDataAndRefresh(months);
                        }
                );
    }
    /*
        flatMap은 아이템을 여러개로 분리할 수 있다
     */
    public void doFlatMap(View view){
        months.clear();
        // 2. 구독자
        observable
                .subscribeOn(Schedulers.io())              // 옵저버블의 thread를 지정
                .observeOn(AndroidSchedulers.mainThread()) // 옵저버의 thread를 지정
                .filter(str->str.equals("3월")?false:true)
                .flatMap(item->{
                    return Observable.fromArray(new String[]{"name"+item, "["+item+"]"});
                })
                .subscribe(
                        str -> {
                            months.add(str);
                            adapter.setDataAndRefresh(months);
                        }
                );
    }
    /*
        복수개의 옵저버블을 하나로 묶어준다
     */
    public void doZip(View view){
        months.clear();
        // 2. 구독자
        observableZip
                .subscribeOn(Schedulers.io())              // 옵저버블의 thread를 지정
                .observeOn(AndroidSchedulers.mainThread()) // 옵저버의 thread를 지정
                .subscribe(
                        str -> {
                            months.add(str);
                            adapter.setDataAndRefresh(months);
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