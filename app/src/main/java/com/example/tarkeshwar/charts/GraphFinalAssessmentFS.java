package com.example.tarkeshwar.charts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 21/1/17.
 */

public class GraphFinalAssessmentFS extends AppCompatActivity {
    private String url="http://192.168.0.200:8282/BHT/GraphFinalAssessmentFS";
    private String dataFromServer;
    private ObjectMapper mapper;
    private GetSetFinal getSetFinal;
    /*GraphFinalAssessmentFS
{"total":939,"male":1485,"female":2424} */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            getSetFinal = mapper.readValue(dataFromServer, GetSetFinal.class);
            System.out.println("Getter Setter Done - "+ getSetFinal);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BarChart chart = new BarChart(this);
        chart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Topup");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(getSetFinal.getFemale(), 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(getSetFinal.getMale(), 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(getSetFinal.getTotal(), 2); // Mar
        valueSet1.add(v1e3);
       /* BarEntry v1e4 = new BarEntry(getSetTopup.getScheduled(), 3); // Apr
        valueSet1.add(v1e4);
*/

      /*  ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);
*/
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "TOP UP");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
       /* BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Foundation Skills");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
*/
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        // dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        //completed":1568,"ongoing":2263,"scheduled":0,"projected":0
        xAxis.add("Female");
        xAxis.add("Male");
        xAxis.add("Total");

        return xAxis;
    }
}
