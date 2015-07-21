package jp.androidbook.myapp.googlemapsapplication01;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;

    //住所の受け取り
    Intent mapsIntent = getIntent();
    private String add = mapsIntent.getStringExtra("add");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {

            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
                    //↑googleMapの生成
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        //緯度経度の初期化
        double latitude = 0;
        double longitude = 0;

        try{
            //受け取った住所を緯度経度に設定
            Address address = getLatLongFromLocationActivity(add);
            latitude = address.getLatitude();
            longitude = address.getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18);
        mMap.moveCamera(cu);

        //設定した緯度経度にマーカーを設定
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
    private Address getLatLongFromLocationActivity(String locationName) throws IOException{
        Geocoder gcoder = new Geocoder(this, Locale.getDefault());

        List<Address> addressesList = gcoder.getFromLocationName(locationName,1);
        Address address = addressesList.get(0);

        return address;
    }
}
