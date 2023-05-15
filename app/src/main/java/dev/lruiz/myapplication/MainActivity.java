package dev.lruiz.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    int requestCode = 200;
    double latitud,longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLat = findViewById(R.id.textview1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            verificarPermisos();

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat = findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        latitud = location.getLatitude();
        longitud = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    private void verificarPermisos(){
        int permisoLoc1 = ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION);
        int permisoLoc2 = ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permisoLoc1 == PackageManager.PERMISSION_GRANTED){
            //Método permision aceptado
        }else{
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},requestCode);
        }

        if(permisoLoc2 == PackageManager.PERMISSION_GRANTED){
            //Método permision aceptado
        }else{
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},requestCode);
        }

    }

}