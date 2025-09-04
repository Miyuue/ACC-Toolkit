package com.miyuue.acctoolkit;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoadHazardCalc extends AppCompatActivity {
    private Spinner measuredTreadDepthSpinner;
    private Spinner startingTreadDepthSpinner;
    private EditText enterCostEditText;
    private TextView resultTextView;

    private static final float[][] percentageLookUp =
    {
            // This look up came from an official chart on my ACC coach's wall

            { // OT: 8/32
                1.0f, // TR: 2/32
                0.83f, // TR: 3/32
                0.67f, // TR: 4/32
                0.5f, // TR: 5/32
                0.33f, // TR: 6/32
                0.17f, // TR: 7/32
                0.0f // TR: 8/32
            },

            { // OT: 9/32

            },

            { // OT: 10/32

            },

            { // OT: 11/32

            },

            { // OT: 12/32

            },

            { // OT: 13/32

            },

            { // OT: 14/32

            },

            { // OT: 15/32

            },

            { // OT: 16/32

            },

            { // OT: 17/32

            },

            { // OT: 18/32

            },

            { // OT: 19/32

            },

            { // OT: 20/32

            },

            { // OT: 21/32

            },

            { // OT: 22/32

            },

            { // OT: 23/32

            },

            { // OT: 24/32

            },

            { // OT: 25/32

            },

            { // OT: 26/32

            },

            { // OT: 27/32

            },

            { // OT: 28/32

            },

            { // OT: 29/32

            },

            { // OT: 30/32

            },

            { // OT: 31/32

            },

            { // OT: 32/32

            },
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_hazard_calc);

        measuredTreadDepthSpinner = findViewById(R.id.measuredTreadDepthSpinner);
        startingTreadDepthSpinner = findViewById(R.id.startingTreadDepthSpinner);
        enterCostEditText = findViewById(R.id.enterCost);
        resultTextView = findViewById(R.id.resultText);
    }

    private float lookUpPercentageToPay(int startingDepth, int measuredDepth) {
        return percentageLookUp[startingDepth - 8][measuredDepth - 2];
    }

    public void calculateCost(View view) {
        // Get input values
        String measuredSelectionString = measuredTreadDepthSpinner.getSelectedItem().toString();
        String startingSelectionString = startingTreadDepthSpinner.getSelectedItem().toString();
        String currentCostString = enterCostEditText.getText().toString();

        // This collects the numerator of the selected values as an int
        int measuredSelection = Integer.decode(measuredSelectionString.split("/")[0]);
        int startingSelection = Integer.decode(startingSelectionString.split("/")[0]);

        // Ensure a valid float
        float currentCost;
        try {
            currentCost = Float.parseFloat(currentCostString);
        }
        catch(Exception e) {
            // Show error message
            resultTextView.setTextColor(Color.RED);
            resultTextView.setText(R.string.enterCostError);
            return;
        }

        // Current tread depth cannot be bigger than the starting tread depth
        if (measuredSelection > startingSelection) {
            // Show error message
            resultTextView.setTextColor(Color.RED);
            resultTextView.setText(R.string.measuredGTStartingError);
            return;
        }

        // Calculate cost
        float customerCost = currentCost * lookUpPercentageToPay(startingSelection, measuredSelection);

        // Show results
        resultTextView.setTextColor(Color.BLUE);
        resultTextView.setText("Customer pays: $".concat(String.valueOf(customerCost)));
    }
}