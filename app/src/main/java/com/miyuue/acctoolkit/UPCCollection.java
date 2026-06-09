package com.miyuue.acctoolkit;

import android.content.Intent;
import android.os.Bundle;

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

        UPC upc = new UPC();
        db.addUPC(upc);
    }

    public void openBarcodeDisplay(UPC upc) {
        Intent intent = new Intent(this, BarcodeDisplay.class);
        intent.putExtra("upc", upc.getUPC());
        startActivity(intent);
    }
}