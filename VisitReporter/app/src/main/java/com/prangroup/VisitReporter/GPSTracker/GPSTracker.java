package com.prangroup.VisitReporter.GPSTracker;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.prangroup.VisitReporter.BusinessObjects.User;
import com.prangroup.VisitReporter.R;
import com.prangroup.VisitReporter.Utility.Calender;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manik on 4/21/2017.
 */
public class GPSTracker extends Service implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    private static final long INTERVAL = 1000 * 30;
    private static final long FASTEST_INTERVAL = 1000 * 15;
    public static Location location=new Location("");
    GPSBinder binder = new GPSBinder();
    User user;
    public static double lat = 0;
    public static double lon = 0;
    RequestQueue queue;
    String url;
    public GPSTracker() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        user =new User(getApplicationContext());
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        queue = Volley.newRequestQueue(getApplicationContext());
        url = getString(R.string.data_source)+"trackgps.php";
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isLocationEnabled(GPSTracker.this)) {
            Toast.makeText(GPSTracker.this, "GPS Device OFF", Toast.LENGTH_SHORT).show();
        }
        mGoogleApiClient.connect();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }

    public boolean isLocationEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        return gps_enabled;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mGoogleApiClient.connect();
        return binder;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(final Location location) {
        Log.e("gps traking",url);
        lat=location.getLatitude();
        lon=location.getLongitude();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id", user.getUserId()+"");
                params.put("lat",location.getLatitude()+"");
                params.put("lon",location.getLongitude()+"");
                params.put("timestamp", Calender.currentTimeStamp()+"");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public class GPSBinder extends Binder {
        public GPSTracker getService() {
            return GPSTracker.this;
        }
    }
}