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
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 17/1/17.
 */

public class TOPUPDivision extends AppCompatActivity {
    private String url="http://192.168.0.200:8282/BHT/GraphDivisionFS";
    private String dataFromServer;
    private ObjectMapper mapper;
    GetSetDivision getSetDivision;
    //private GetSetDivision[] getSetDivision;
    /*GraphDivisionFS
[["0",0],["Barisal",184],["Chittagong",1040],["Comilla",263],["Dhaka",3813],["Dinajpur",253],["Gopalgunj",0],["khulna",231],["Kushtia",112],["Nohakali",188],["Rajshahi",334],["Rangpur",580],["Sylhet",168],["Tangail",190]] */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            List list = mapper.readValue(dataFromServer, List.class);
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i).toString());
                String[] split = list.get(i).toString().split(",");
                System.out.println(split[0].substring(1));
                System.out.println(split[1].substring(0,split[1].length()-1));

                labels.add(split[0].substring(1));
                entries.add(new Entry(Integer.parseInt(split[1].substring(0,split[1].length()-1).trim()),i));

            }

        }catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setContentView(R.layout.top_up_division);

                // creating list of entry


        LineDataSet dataset = new LineDataSet(entries, "Divisions");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setDrawCubic(true);


        LineData data = new LineData(labels, dataset);
       lineChart.setData(data);
       lineChart.setDescription("Description");;
    }
}
