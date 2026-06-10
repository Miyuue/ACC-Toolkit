package com.miyuue.acctoolkit;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BarcodeDisplay extends AppCompatActivity {
    TextView upcNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_display);

        // Set views and get passed over UPC
        String upcNumber = getIntent().getStringExtra("upc");
        upcNumberTextView = findViewById(R.id.upcNumberTextView);
        upcNumberTextView.setText(upcNumber);
    }

    // Deletes the UPC passed to this class in the Intent.
    // (removing only needs the UPC code, name doesn't matter)
    public void deleteThisUPC(View view) {
        UPCDatabase db = new UPCDatabase(this);
        db.removeUPC(new UPC("null", getIntent().getStringExtra("upc")));
        finish();
    }
}