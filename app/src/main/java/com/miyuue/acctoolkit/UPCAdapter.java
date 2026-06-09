package com.miyuue.acctoolkit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UPCAdapter extends RecyclerView.Adapter<UPCAdapter.ViewHolder> {
    private ArrayList<UPC> upcList;
    private UPCCollection upcCollection;

    public UPCAdapter(ArrayList<UPC> upcList, UPCCollection upcCollection) {
        this.upcList = upcList;
        this.upcCollection = upcCollection;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        UPC upc;
        TextView upcName;
        ImageButton displayBarcodeBtn;

        public ViewHolder(View upcView) {
            super(upcView);

            upcName = upcView.findViewById(R.id.upcNameTextView);
            displayBarcodeBtn = upcView.findViewById(R.id.displayBarcodeBtn);
        }
    }

    // Creates new views
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upc, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replaces the contents of a view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        UPC upc = upcList.get(position);

        viewHolder.upc = upc;
        viewHolder.upcName.setText(upc.getName());
        viewHolder.displayBarcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upcCollection.openBarcodeDisplay(upc);
            }
        });
    }

    // Returns size of dataset (list of upcs)
    @Override
    public int getItemCount() { return upcList.size(); }
}
