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
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tarkeshwar on 21/1/17.
 */

public class GraphDivisionTopup extends AppCompatActivity {
    private String url = "http://192.168.0.200:8282/BHT/GraphDivisionTopup";
    private String dataFromServer;
    private ObjectMapper mapper;
    private List list;

    /*GraphDivisionTopup
    * [["Barisal",41],["Chittagong",252],["Comilla",11],["Dhaka",2829],["Dinajpur",50],["Gopalgunj",0],["Jessore",40],["Khulna",84],["Kushtia",155],["Nohakali",33],["Rajshahi",192],["Rangpur",20],["Sylhet",74],["Tangail",50]]
*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Entry> entries = new ArrayList();
        ArrayList<String> labels = new ArrayList<String>();
        try {

            dataFromServer = new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - " + dataFromServer);
            mapper = new ObjectMapper();
            list = mapper.readValue(dataFromServer, List.class);
            System.out.println("Getter Setter Done - " + list);

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
                String[] split = list.get(i).toString().split(",");
                System.out.println(split[0].substring(1));
                System.out.println(split[1].substring(0, split[1].length() - 1));

                labels.add(split[0].substring(1));
                entries.add(new Entry(Integer.parseInt(split[1].substring(0, split[1].length() - 1).trim()), i));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        //setContentView(R.layout.foundation_skills);
        PieChart pieChart = new PieChart(this);
        pieChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(pieChart);
// creating data values

        PieDataSet dataset = new PieDataSet(entries, "Foundation Skills");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);


        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
        pieChart.setDescription("Description");

    }
}