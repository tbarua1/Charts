package com.example.tarkeshwar.charts;

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
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 21/1/17.
 */

public class GraphDivisionOverAll extends AppCompatActivity {
    private String url="http://192.168.0.200:8282/BHT/GraphDivisionOverAll";
    private String dataFromServer;
    private ObjectMapper mapper;
    private List list;
    /*GraphDivisionOverAll
[["0",0],["Barisal",225],["Chittagong",1292],["Comilla",274],["Dhaka",6642],["Dinajpur",303],["Gopalgunj",0],["Jessore",40],["khulna",315],["Kushtia",267],["Nohakali",221],["Rajshahi",526],["Rangpur",600],["Sylhet",242],["Tangail",240]] */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PieChart pieChart = new PieChart(this);
        pieChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(pieChart);
        ArrayList<Entry> entries= new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            list = mapper.readValue(dataFromServer, List.class);
            System.out.println("Getter Setter Done - "+list);
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i).toString());
                String[] split = list.get(i).toString().split(",");
                System.out.println(split[0].substring(1));
                System.out.println(split[1].substring(0,split[1].length()-1));

                labels.add(split[0].substring(1));
                entries.add(new Entry(Integer.parseInt(split[1].substring(0,split[1].length()-1).trim()),i));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PieDataSet dataset = new PieDataSet(entries,"Foundation Skills");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
        pieChart.setDescription("Description");
    }
}
