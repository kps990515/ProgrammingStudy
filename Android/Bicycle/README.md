# Google Map

### MapsActivity
- MapsActivity를 자동으로 생성해주면
- Map이 Fragment로 자동생성 + OnMapReadyCallback implements

### 메인파트
- mapFragment를 생성
- 맵이 사용할 준비가 되었는지를 확인(getMapAsync)
- 준비가 됐으면 onMapReady()호출

```java
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
```

### onMapReady()
- 맵이 준비되었으면 load()를 통해 json데이터 읽어오기

```java
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        load();
    }
```

### load()
- json정보를 읽어오고
- 다 읽어왔으면 marker()를 통해 지도에 marker찍기

```java
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
```

### marker()

```java
    public void marker(){
        for(Row item : data){
            LatLng latLng = new LatLng(Double.parseDouble(item.getLAT()),Double.parseDouble(item.getLNG()));
            mMap.addMarker(new MarkerOptions().position(latLng).title(item.getADDRESS()));
        }
        LatLng first = new LatLng(37.5399344,126.9737224);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(first,10));
    }
}
```
