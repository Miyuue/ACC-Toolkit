package com.miyuue.acctoolkit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddUPCBottomSheet extends BottomSheetDialogFragment {
    EditText upcNameEditText;
    EditText upcCodeEditText;
    Button submitNewUPCBtn;
    UPCCollection upcCollection;

    public AddUPCBottomSheet(UPCCollection upcCollection) {
        this.upcCollection = upcCollection;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_upc_bottom_sheet, container, false);

        upcNameEditText = view.findViewById(R.id.upcNameEditText);
        upcCodeEditText = view.findViewById(R.id.upcCodeEditText);
        submitNewUPCBtn = view.findViewById(R.id.submitNewUPCBtn);

        // Submit new item
        submitNewUPCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UPCDatabase db = new UPCDatabase(view.getContext());

                String upcName = upcNameEditText.getText().toString();
                String upcCode = upcCodeEditText.getText().toString();

                // Show error if inputs empty
                if (upcName.isEmpty() || upcCode.isEmpty()) {
                    Toast.makeText(view.getContext(), "Fill out any empty inputs before submitting!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add new UPC to db and close view
                db.addUPC(new UPC(upcName, upcCode));
                upcCollection.refreshRecyclerView();
                dismiss();
            }
        });

        return view;
    }
}
