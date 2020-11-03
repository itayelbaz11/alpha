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

    public void pedometer(View view) {
        Intent si=new Intent(this,pedometer.class);
        startActivity(si);
    }

    public void gps(View view) {
        Intent si=new Intent(this,GPS.class);
        startActivity(si);
    }
}
