package com.karrel.googlemapsample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.karrel.googlemapsample.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mContext = this;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
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
        // 컴스텀 GoogleMap.InfoWindowAdapter를 지정해줍니다.
        // snippet의 줄바꿈처리를 지원해줍니다.
        mMap.setInfoWindowAdapter(infoWindowAdapter);

        // Add a marker in Sydney and move the camera
        LatLng l = new LatLng(37.5606750, 127.0800380);
        MarkerOptions options = new MarkerOptions();
        // 위도경도를 넣어 줍니다.
        options.position(l);
        // 타이틀을 넣어줍니다.
        options.title("중곡동");
        // snippet을 넣어줍니다. (작은정보라는 뜻)
        options.snippet("서울시 광진구 중곡동 \n서울시 광진구 중곡동 \n서울시 광진구 중곡동 \n서울시 광진구 중곡동");

        // 마커의 색상이 변경된 아이콘을 지정한다.
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        Marker m = mMap.addMarker(options);

        // 설정된 마커정보를 항상 보여지게 합니다.
        m.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(l));
    }

    private GoogleMap.InfoWindowAdapter infoWindowAdapter = new GoogleMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            // 타이틀과 snippet의 커스텀뷰를 리턴해줍니다.
            // 아마도 내부적으로 뷰의 순서를 가지고 Title과 Snippet뷰를 지정해주는거같습니다.
            // 그래서 TextView를 순서대로 2개를 넣으면
            // 처음넣은것은 TitleView가 되고 두번째 넣은것은 Snippet뷰가 됩니다.

            LinearLayout info = new LinearLayout(mContext);
            info.setOrientation(LinearLayout.VERTICAL);

            TextView title = new TextView(mContext);
            title.setTextColor(Color.BLACK);
            title.setGravity(Gravity.CENTER);
            title.setTypeface(null, Typeface.BOLD);
            title.setText(marker.getTitle());

            TextView snippet = new TextView(mContext);
            snippet.setTextColor(Color.GRAY);
            snippet.setText(marker.getSnippet());

            info.addView(title);
            info.addView(snippet);
            return info;
        }
    };
}
