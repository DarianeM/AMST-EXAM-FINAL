package com.example.amstdetectorfuego;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class estadoBateria extends AppCompatActivity {

    private Integer total=100,consumido=25, valNoConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_bateria);
    }

    public void consumoActual(){
        PieChart pieCharts = findViewById(R.id.pieChart);

        //total -> valEstimadoPorMes
        //real -> valConsumo
        //restante -> valNoConsumo

        valNoConsumo = total - consumido;


        ArrayList<PieEntry> v = new ArrayList<>();
        v.add(new PieEntry(consumido,""));
        v.add(new PieEntry(valNoConsumo,""));

        PieDataSet pieDataSet = new PieDataSet(v,"valores");
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieCharts.setData(pieData);
        pieCharts.setCenterText(consumido.toString() + " %");
        pieCharts.setUsePercentValues(true);
        pieCharts.setCenterTextSize(30f);
        pieCharts.setCenterTextTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        pieCharts.setCenterTextColor(Color.DKGRAY);
        pieCharts.getData().setDrawValues(false);
        pieCharts.getLegend().setEnabled(false);
        pieCharts.getDescription().setEnabled(false);
        pieCharts.setTransparentCircleColor(Color.WHITE);
        pieCharts.setRotationEnabled(false);
    }
}