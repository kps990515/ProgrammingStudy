package org.andriodtown.bicycle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.andriodtown.bicycle.model.Json;
import org.andriodtown.bicycle.model.Row;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Row> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // 맵이 사용할 준비가 되었는지를 비동기로 확인하는 작업
        mapFragment.getMapAsync(this);
        // 맵이 사용할 준비가 되었으면 -> OnMapReadyCallback.onMapReady를 호출
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        load();
    }


    private void load(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String str = Remote.getData("http://openapi.seoul.go.kr:8088/5a7779586d6b707333336f6a725464/json/GeoInfoBikeConvenientFacilitiesWGS/1/100");
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                Json json = gson.fromJson(s,Json.class);
                Row [] row = json.getGeoInfoBikeConvenientFacilitiesWGS().getRow();
                data = Arrays.asList(row);

                marker();
            }
        }.execute();
    }
    public void marker(){
        for(Row item : data){
            LatLng latLng = new LatLng(Double.parseDouble(item.getLAT()),Double.parseDouble(item.getLNG()));
            mMap.addMarker(new MarkerOptions().position(latLng).title(item.getADDRESS()));
        }
        LatLng first = new LatLng(37.5399344,126.9737224);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(first,10));
    }


}