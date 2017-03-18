package com.example.tarkeshwar.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 17/1/17.
 */

public class FoundationSkills extends AppCompatActivity {
    private String url="http://27.147.210.136:8080/BHT/GraphFS";
    private String dataFromServer;
    private ObjectMapper mapper;
    private GetSetTopup getSetTopup;
    /*GraphFS
    * GraphFS
{"completed":3485,"ongoing":3871,"scheduled":0,"projected":0} */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            getSetTopup = mapper.readValue(dataFromServer, GetSetTopup.class);
            System.out.println("Getter Setter Done - "+ getSetTopup);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setContentView(R.layout.foundation_skills);
        PieChart pieChart = new PieChart(this);
        pieChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(pieChart);
// creating data values
                ArrayList <Entry> entries = new ArrayList();
                entries.add(new Entry(getSetTopup.getCompleted(), 0));
                entries.add(new Entry(getSetTopup.getOngoing(), 1));
                entries.add(new Entry(getSetTopup.getProjected(), 2));
                entries.add(new Entry(getSetTopup.getScheduled(), 3));

    PieDataSet dataset = new PieDataSet(entries,"Foundation Skills");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
                labels.add("Completed");
                labels.add("Ongoing");
                labels.add("Projected");
                labels.add("Scheduled");
        PieData data = new PieData(labels, dataset);
    pieChart.setData(data);
     pieChart.setDescription("Description");

    }
}
