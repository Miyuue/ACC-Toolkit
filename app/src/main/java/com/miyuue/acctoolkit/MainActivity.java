package com.miyuue.acctoolkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToUPCCollection(View view) {
        Intent intent = new Intent(this, UPCCollection.class);
        startActivity(intent);
    }

    public void goToRoadHazardCalc(View view) {
        Intent intent = new Intent(this, RoadHazardCalc.class);
        startActivity(intent);
    }
}