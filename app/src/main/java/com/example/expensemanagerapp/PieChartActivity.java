package com.example.expensemanagerapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {
    PieChart pieChart;
    int[] colorClassArray = new int[] {Color.CYAN, Color.RED};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChart = findViewById(R.id.pieChart);

        PieDataSet pieDataSet = new PieDataSet(dataValues1(),"");
        pieDataSet.setColors(colorClassArray);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setDrawEntryLabels(true);
        pieChart.setUsePercentValues(false);
        pieChart.setCenterTextSize(10);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleRadius(0);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private ArrayList<PieEntry> dataValues1() {
        ArrayList<PieEntry> dataVals1 = new ArrayList<>();
        dataVals1.add(new PieEntry(2000, "Income"));
        dataVals1.add(new PieEntry(1200, "Outcome"));
        return dataVals1;
    }
}
