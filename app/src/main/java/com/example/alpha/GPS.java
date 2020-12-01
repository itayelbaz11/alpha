package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.security.Permissions;

public class GPS extends AppCompatActivity  implements SensorEventListener{

    public static final int PERMISSIONS_FINE_LOCATION=99;
    /**
     * references to UI objects
     */
    TextView tv1,tv2,tv3,X,Y,Z;

    /**
     * Google's API for location services.
     */
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;

    private SensorManager sensorManager;
    Sensor accelerometer;

    String x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_s);

        /**
         * give each UI variable a value
         */


        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
        tv3=(TextView) findViewById(R.id.tv3);
        X=(TextView) findViewById(R.id.x);
        Y=(TextView) findViewById(R.id.y);
        Z=(TextView) findViewById(R.id.z);

        /**
         * Set all properties of locationRequest.
         */

        locationRequest=new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(100);


        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(GPS.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case PERMISSIONS_FINE_LOCATION:
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                updateGPS();
            }
            else{
                Toast.makeText(this,"this apps requires permission to be granted in order to work properly",Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
        }
    }

    /**
     * get permissions from the user to track gps
     * get the current location from the fused client.
     * update the UI-i.e. set all properties in their associated text view items
     */
    private void updateGPS(){
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(GPS.this);

        /**
         * User provided permission
         */
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
             fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                 @Override
                 public void onSuccess(Location location) {
                     /**
                      * we got permissions. put the values of location. xxx into the UI components.
                      */
                     updateUIValues(location);
                 }
             });
        }
        else{
            /**
             *  permission not granted yet
             */
             if (Build.VERSION.SDK_INT>=23){
                 requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION} ,PERMISSIONS_FINE_LOCATION);
             }
        }
    }

    /**
     * Update all of the text view objects with the new location.
     */
    private void updateUIValues(Location location) {
          tv1.setText("lat:"+String.valueOf(location.getLatitude()));
          tv2.setText("long:"+String.valueOf(location.getLongitude()));
          tv3.setText("bearing:"+String.valueOf(location.getBearing()));
          X.setText("X:"+x);
          Y.setText("Y:"+y);
          Z.setText("Z:"+z);

    }

    /**
     * a button that starts the location finding in this moment.
     */
    public void btn(View view) {
        updateGPS();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
         x=String.valueOf(sensorEvent.values[0]);
         y=String.valueOf(sensorEvent.values[1]);
         z=String.valueOf(sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}


