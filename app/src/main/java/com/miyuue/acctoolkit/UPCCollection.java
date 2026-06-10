package com.miyuue.acctoolkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UPCCollection extends AppCompatActivity {
    private final UPCDatabase db;
    RecyclerView recyclerView;
    UPCAdapter adapter;

    public UPCCollection() {
        db = new UPCDatabase(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upc_collection);

        // Setup recycler view
        ArrayList<UPC> upcList = db.getAllUPCs();
        recyclerView = findViewById(R.id.upcRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UPCAdapter(upcList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshRecyclerView();
    }

    public void refreshRecyclerView() {
        ArrayList<UPC> upcList = db.getAllUPCs();
        adapter = new UPCAdapter(upcList, this);
        recyclerView.setAdapter(adapter);
    }

    // This is the onClick method for the floating add button
    // Opens the bottom sheet for adding a new UPC to the db
    public void openAddUPCSheet(View view) {
        AddUPCBottomSheet sheet = new AddUPCBottomSheet(this);
        sheet.show(getSupportFragmentManager(), "AddUPCBottomSheet");
    }

    // This is the onClick method for when the displayBarcode button on any of the UPCs is clicked
    // Opens up the Barcode Display activity
    // Passes the upc to the activity
    public void openBarcodeDisplay(UPC upc) {
        Intent intent = new Intent(this, BarcodeDisplay.class);
        intent.putExtra("upc", upc.getUPC());
        startActivity(intent);
    }
}