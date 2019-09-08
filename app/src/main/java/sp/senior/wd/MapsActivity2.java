package sp.senior.wd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Vector;

import sp.senior.wd.Model.MyPlaces;
import sp.senior.wd.Model.Results;
import sp.senior.wd.Remote.IGoogleAPIService;
import sp.senior.wd.Common;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity2 extends FragmentActivity
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnPolylineClickListener {

    private static final int MY_PERMISSION_CODE = 1000;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private Location mLastLocation;
    private double latitude, longitude;
    private LocationRequest mLocationRequest;

    IGoogleAPIService mService;


    double[] myplace1;
    double[] myplace2;
    double[] myplace3;
    double[] myplace4;
    double[] myplace5;

    String place_name1;
    String place_name2;
    String place_name3;
    String place_name4;
    String place_name5;

    double[] lat;
    double[] lng;
    String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Init Service
        mService = Common.getGoogleAPIService();
 //       myplace1 = new LatLng(40.750960, -73.989484);

        //request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        Intent i = getIntent();
        myplace1 = i.getDoubleArrayExtra("place1");
        myplace2 = i.getDoubleArrayExtra("place2");
        myplace3 = i.getDoubleArrayExtra("place3");
        myplace4 = i.getDoubleArrayExtra("place4");
        myplace5 = i.getDoubleArrayExtra("place5");
        place_name1=i.getStringExtra("place_name1");
        place_name2=i.getStringExtra("place_name2");
        place_name3=i.getStringExtra("place_name3");
        place_name4=i.getStringExtra("place_name4");
        place_name5=i.getStringExtra("place_name5");

        lat = new double[] {myplace1[0], myplace2[0], myplace3[0], myplace4[0], myplace5[0]};
        lng = new double[] {myplace1[1], myplace2[1], myplace3[1], myplace4[1], myplace5[1]};
        name = new String[] {place_name1, place_name2, place_name3, place_name4, place_name5};
        double temp_lat = 0;
        double temp_lng = 0;
        String temp_name = null;

        for (int k = 0; k < lat.length; k++){
            for (int t = 0; t < lat.length-1-k; t ++){
                if (lat[t] > lat[t+1]){
                    temp_lat = lat[t+1];
                    temp_lng = lng[t+1];
                    temp_name = name[t+1];
                    lat[t+1] = lat[t];
                    lat[t] = temp_lat;
                    lng[t+1] = lng[t];
                    lng[t] = temp_lng;
                    name[t+1] = name[t];
                    name[t] = temp_name;
                }
            }
        }

    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, MY_PERMISSION_CODE);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, MY_PERMISSION_CODE);
            }
            return false;
        }else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_CODE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if (mGoogleApiClient == null)
                            buildGoogleApiClien();
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //init google play services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClien();
                mMap.setMyLocationEnabled(true);
            }
        }
        else{
            buildGoogleApiClien();
            mMap.setMyLocationEnabled(true);
        }

        LatLng place1 = new LatLng(lat[0], lng[0]);
        MarkerOptions markerOptions1 = new MarkerOptions().position(place1);
        String titleStr = name[0];
        markerOptions1.title(titleStr);
        mMap.addMarker(markerOptions1);

        LatLng place2 = new LatLng(lat[1], lng[1]);
        MarkerOptions markerOptions2 = new MarkerOptions().position(place2);
        String titleStr2 = name[1];
        markerOptions2.title(titleStr2);
        mMap.addMarker(markerOptions2);

        LatLng place3 = new LatLng(lat[2], lng[2]);
        MarkerOptions markerOptions3 = new MarkerOptions().position(place3);
        String titleStr3 = name[2];
        markerOptions3.title(titleStr3);
        mMap.addMarker(markerOptions3);

        LatLng place4 = new LatLng(lat[3], lng[3]);
        MarkerOptions markerOptions4 = new MarkerOptions().position(place4);
        String titleStr4 = name[3];
        markerOptions4.title(titleStr4);
        mMap.addMarker(markerOptions4);

        LatLng place5 = new LatLng(lat[4], lng[4]);
        MarkerOptions markerOptions5 = new MarkerOptions().position(place5);
        String titleStr5 = name[4];
        markerOptions5.title(titleStr5);
        mMap.addMarker(markerOptions5);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(place2));

        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(place1, place2, place3, place4, place5));
        polyline1.setTag("A");

        // Position the map's camera near Alice Springs in the center of Australia,

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);

    }

    private synchronized void buildGoogleApiClien(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, this);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        LatLng latlng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        if (mGoogleApiClient != null)
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this );
    }

    public void nearBySearch(View view) {
        nearByPlace("restaurant");
    }

    private void nearByPlace(String placeType) {
        mMap.clear();
        String url = getUrl(latitude,longitude,placeType);
        mService.getNearByPlaces(url)
                .enqueue(new Callback<MyPlaces>() {
                    @Override
                    public void onResponse(Call<MyPlaces> call, Response<MyPlaces> response) {
                        if (response.isSuccessful()){
                            for (int i = 0; i < response.body().getResults().length; i ++){
                                MarkerOptions markeroptions = new MarkerOptions();
                                Results googlePlace = response.body().getResults()[i];
                                double lat = Double.parseDouble(googlePlace.getGeometry().getLocation().getLat());
                                double lng = Double.parseDouble(googlePlace.getGeometry().getLocation().getLng());
                                String placeName = googlePlace.getName();
                                //String vicinity = googlePlace.getVicinity();
                                //String type = googlePlace.getTypes().toString();
                                String rating = googlePlace.getRating();
                                String info = placeName + ", " + "Rating: " + rating;
                                LatLng latlng = new LatLng(lat, lng);
                                markeroptions.position(latlng);
                                markeroptions.title(info);

//                                if (placeType.equals("restaurant")){
//                                    //markeroptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_restaurant));
//                                    markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
//                                }else{
//                                    markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//                                }
                                markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                                mMap.addMarker(markeroptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                            }
                        }
                        else {
                            Log.i("msg","failure");
                        }
                    }

                    @Override
                    public void onFailure(Call<MyPlaces> call, Throwable t) {

                    }
                });
    }

    private String getUrl(double latitude, double longitude, String placeType) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+1000);
        googlePlaceUrl.append("&type="+placeType);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+getResources().getString(R.string.browser_key));
        Log.d("getUrl",googlePlaceUrl.toString());
        return googlePlaceUrl.toString();
    }

    private LatLng transLatlng(Vector place){
        double lat = Double.parseDouble(place.get(0).toString());
        double lng = Double.parseDouble(place.get(0).toString());
        return  new LatLng(lat,lng);
//        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
//                .clickable(false)
//                .add(myplace1, myplace2, myplace3, myplace4, myplace5));
//        polyline1.setTag("A");

        // Position the map's camera near Alice Springs in the center of Australia,

        // Set listeners for click events.
 //       mMap.setOnPolylineClickListener((GoogleMap.OnPolylineClickListener) this);
    }

    private void putMarker(LatLng myplace){
        MarkerOptions markerOptions = new MarkerOptions().position(myplace);
//        String titleStr = getAddress(location);
 //       markerOptions.title(titleStr);
        mMap.addMarker(markerOptions);
    }


    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}
