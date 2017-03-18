package com.example.tarkeshwar.charts;

import android.graphics.Color;
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
 * Created by tarkeshwar on 17/1/17.
 */

public class ITESFSFinal extends AppCompatActivity{
    private String url="http://192.168.0.200:8282/BHT/GraphFinalAssessmentFS";
    private String dataFromServer;
    private ObjectMapper mapper;
    private  GetSetFinal getSetGraphDivisionFS;
    /*GraphFinalAssessmentFS
{"total":939,"male":1485,"female":2424} */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            getSetGraphDivisionFS = mapper.readValue(dataFromServer, GetSetFinal.class);
            System.out.println("Getter Setter Done - "+getSetGraphDivisionFS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(getSetGraphDivisionFS.getTotal(), 0));
        entries.add(new BarEntry(getSetGraphDivisionFS.getMale(), 1));
        entries.add(new BarEntry(getSetGraphDivisionFS.getFemale(), 2));


        BarDataSet dataset = new BarDataSet(entries, "Final ITES Foundation Skills");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Total");
        labels.add("Male");
        labels.add("Female");
        BarChart chart = new BarChart(this);
        chart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Foundation Skills");
    }
}
