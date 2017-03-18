package com.example.tarkeshwar.charts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 21/1/17.
 */

public class GraphOverall extends AppCompatActivity {
    private String url="http://192.168.0.200:8282/BHT/GraphOverall";
    private String dataFromServer;
    private ObjectMapper mapper;

    private GetSetTopup getSetTopup;
    /*GraphOverall
{"completed":5053,"ongoing":6134,"scheduled":0,"projected":0} */
    //{"completed":5053,"ongoing":6134,"scheduled":0,"projected":0}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LineChart lineChart = new LineChart(this);
        lineChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(lineChart);
        ArrayList<Entry> entries= new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            getSetTopup = mapper.readValue(dataFromServer, GetSetTopup.class);
            System.out.println("Getter Setter Done - "+getSetTopup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        entries.add(new Entry(getSetTopup.getCompleted(),0));
        entries.add(new Entry(getSetTopup.getOngoing(),1));
        entries.add(new Entry(getSetTopup.getScheduled(),2));
        entries.add(new Entry(getSetTopup.getProjected(),3));
        labels.add("Completed");
        labels.add("Ongoing");
        labels.add("Scheduled");
        labels.add("Projected");
        LineDataSet dataset = new LineDataSet(entries, "Divisions");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setDrawCubic(true);


        LineData data = new LineData(labels, dataset);
        lineChart.setData(data);
        lineChart.setDescription("Description");
    }
}
