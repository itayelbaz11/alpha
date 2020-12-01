package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * moves to pedometer
     * @param view
     */
    public void pedometer(View view) {
        Intent si=new Intent(this,pedometer.class);
        startActivity(si);
    }

    /**
     * moves to GPS
     * @param view
     */
    public void gps(View view) {
        Intent si=new Intent(this,GPS.class);
        startActivity(si);
    }


    /**
     * moves to pedometer
     * @param view
     */
    public void auth(View view) {
        Intent si=new Intent(this,AuthMenu.class);
        startActivity(si);
    }

    public void storage(View view) {
        Intent si=new Intent(this,storge.class);
        startActivity(si);
    }
}
