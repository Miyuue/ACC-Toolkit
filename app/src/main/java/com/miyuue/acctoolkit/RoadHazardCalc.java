package com.miyuue.acctoolkit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.regex.Pattern;

public class RoadHazardCalc extends AppCompatActivity {
    private Spinner measuredTreadDepthSpinner;
    private Spinner startingTreadDepthSpinner;
    private EditText enterCostEditText;
    private TextView resultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_hazard_calc);

        measuredTreadDepthSpinner = findViewById(R.id.measuredTreadDepthSpinner);
        startingTreadDepthSpinner = findViewById(R.id.startingTreadDepthSpinner);
        enterCostEditText = findViewById(R.id.enterCost);
        resultsTextView = findViewById(R.id.resultsText);
    }

    private float calculatePercentageToPay(int originalTread, int remainingTread) {
        if (remainingTread <= 2) return 1;

        if (originalTread == remainingTread) return 0;

        // Formula to calculate wear percentage (what percentage the customer pays)
        // (This was derived from a chart hung up in my manager's office)
        return (float) (originalTread - remainingTread) / (originalTread - 2);
    }

    private void displayFormattedPrice(float price) {
        String priceString = String.format(Locale.getDefault(), "%.2f", price);

        resultsTextView.setText("$".concat(priceString));
        resultsTextView.setVisibility(View.VISIBLE);
    }

    public void calculateCost(View view) {
        // Get input values
        String measuredSelectionString = measuredTreadDepthSpinner.getSelectedItem().toString();
        String startingSelectionString = startingTreadDepthSpinner.getSelectedItem().toString();
        String currentCostString = enterCostEditText.getText().toString();

        // This collects the numerator of the selected values as an int
        int measuredSelection = Integer.decode(measuredSelectionString.split(Pattern.quote("/"))[0]);
        int startingSelection = Integer.decode(startingSelectionString.split(Pattern.quote("/"))[0]);

        // Ensure a valid float
        float currentCost;
        try {
            currentCost = Float.parseFloat(currentCostString);
        }
        catch(Exception e) {
            // Show error message
            Toast.makeText(RoadHazardCalc.this, R.string.enterCostError, Toast.LENGTH_LONG).show();
            return;
        }

        // Ensure a valid cost (valid: 12.34, non-valid: 12.345...)
        if (enterCostEditText.getText().toString().split(Pattern.quote("."))[1].length() > 2) {
            // Show error message
            Toast.makeText(RoadHazardCalc.this, R.string.enterCostError, Toast.LENGTH_LONG).show();
            return;
        }

        // Measured tread depth cannot be bigger than the starting tread depth
        if (measuredSelection > startingSelection) {
            // Show error message
            Toast.makeText(RoadHazardCalc.this, R.string.measuredGTStartingError, Toast.LENGTH_LONG).show();
            return;
        }

        // Calculate cost
        float percentage = calculatePercentageToPay(startingSelection, measuredSelection);
        float customerCost = currentCost * percentage;

        // Show results
        displayFormattedPrice(customerCost);
    }
}