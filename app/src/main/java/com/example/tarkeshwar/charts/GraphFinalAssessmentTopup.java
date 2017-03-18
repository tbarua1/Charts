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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 21/1/17.
 */

public class GraphFinalAssessmentTopup extends AppCompatActivity {
    private String url="http://192.168.0.200:8282/BHT/GraphFinalAssessmentTopup";
    private String dataFromServer;
    private ObjectMapper mapper;
    private GetSetFinal getSetPlacementGraph;

    /*GraphFinalAssessmentTopup
{"total":218,"male":922,"female":1140} */
   // {"total":218,"male":922,"female":1140}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            getSetPlacementGraph = mapper.readValue(dataFromServer, GetSetFinal.class);
            System.out.println("Getter Setter Done - "+getSetPlacementGraph);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PieChart pieChart = new PieChart(this);
        pieChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(pieChart);
// creating data values
        ArrayList <Entry> entries = new ArrayList();
        entries.add(new Entry( getSetPlacementGraph.getMale(), 0));
        entries.add(new Entry(getSetPlacementGraph.getFemale(), 1));
        entries.add(new Entry(getSetPlacementGraph.getTotal(), 2));


        PieDataSet dataset = new PieDataSet(entries,"Final Assessment Topup");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Male");
        labels.add("Female");
        labels.add("Total");
//        labels.add("Scheduled");
        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
        pieChart.setDescription("Description");
       /* PieChart pieChart = new PieChart(this);
        pieChart.setHoleColor(Color.GRAY);
        pieChart.setHoleColorTransparent(true);

        pieChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(pieChart);

        ArrayList<Entry> entries = new ArrayList();
        entries.add(new Entry(getSetPlacementGraph.getTOPUP(), 0));
     entries.add(new Entry(getSetPlacementGraph.getITES(), 1));

        PieDataSet dataset = new PieDataSet(entries,"No C");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("GraphTopup");
        labels.add("ITES Foundation Skills");

        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
        pieChart.setDescription("Description");*/
    }
}
