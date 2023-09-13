package com.example.expensemanagerapp;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    private Spinner filterSpinner;
    private ArrayAdapter<String> filterAdapter;
    private List<String> filterOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        lineChart = findViewById(R.id.lineChart);
        filterSpinner = findViewById(R.id.filterSpinner);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Income");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "Outcome");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        //lineChart.setBackgroundColor(Color.CYAN);
        lineChart.setNoDataText("No f*cking data");
        lineChart.setNoDataTextColor(Color.RED);

        lineChart.setDrawGridBackground(true);
        lineChart.setDrawBorders(true);

        lineDataSet1.setLineWidth(3);
        lineDataSet1.setColor(Color.CYAN);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setCircleColor(Color.GRAY);
        lineDataSet1.setCircleRadius(2);
        lineDataSet1.setValueTextSize(10);

        lineDataSet2.setLineWidth(3);
        lineDataSet2.setColor(Color.RED);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setCircleColor(Color.GRAY);
        lineDataSet2.setCircleRadius(2);
        lineDataSet2.setValueTextSize(10);


        Legend legend = lineChart.getLegend();

        legend.setEnabled(true);
        legend.setTextColor(Color.BLACK);
        legend.setTextSize(15);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormSize(20);
        legend.setXEntrySpace(15);
        legend.setFormToTextSpace(10);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();

        // Calculate screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // Set chart dimensions (adjust padding/margins as needed)
        ViewGroup.LayoutParams layoutParams = lineChart.getLayoutParams();
        layoutParams.width = (int) (screenWidth * 0.9); // 90% of screen width
        layoutParams.height = (int) (screenHeight * 0.5); // 50% of screen height
        lineChart.setLayoutParams(layoutParams);

        // Create filter options
        filterOptions = new ArrayList<>();
        filterOptions.add("Week");
        filterOptions.add("Month");
        filterOptions.add("Year");

        // Create an ArrayAdapter for the Spinner
        filterAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                filterOptions
        );

        // Set the adapter to the Spinner
        filterSpinner.setAdapter(filterAdapter);

        // Set a default filter
        filterSpinner.setSelection(0);

        // Set a listener to handle filter selection
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleFilterSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals1 = new ArrayList<>();
        dataVals1.add(new Entry(0,200));
        dataVals1.add(new Entry(1,240));
        dataVals1.add(new Entry(2,300));
        dataVals1.add(new Entry(3,100));
        dataVals1.add(new Entry(4,280));

        return dataVals1;
    }

    private ArrayList<Entry> dataValues2() {
        ArrayList<Entry> dataVals2 = new ArrayList<>();
        dataVals2.add(new Entry(0,120));
        dataVals2.add(new Entry(2,160));
        dataVals2.add(new Entry(3,230));
        dataVals2.add(new Entry(5,100));
        dataVals2.add(new Entry(7,180));

        return dataVals2;
    }

    private void handleFilterSelection(int position) {
    }

}
