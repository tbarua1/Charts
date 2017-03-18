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

public class GraphDivisionFS extends AppCompatActivity {
    private String url="http://27.147.210.136:8080/BHT/GraphDivisionFS";
    private String dataFromServer;
    private ObjectMapper mapper;
    private List[] list;
    /*GraphDivisionFS
    * [["0",0],["Barisal",184],["Chittagong",1040],["Comilla",263],["Dhaka",3813],["Dinajpur",253],["Gopalgunj",0],["khulna",231],["Kushtia",112],["Nohakali",188],["Rajshahi",334],["Rangpur",580],["Sylhet",168],["Tangail",190]] */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        PieChart pieChart = new PieChart(this);
        pieChart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(pieChart);
        ArrayList<Entry> entries= new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        try {

            dataFromServer= new GetJSON().execute(url).get();
            System.out.println("Downloaded data from Server - "+dataFromServer);
            mapper=new ObjectMapper();
            GetSetDivision[] s= mapper.readValue(dataFromServer, GetSetDivision[].class);
            System.out.println("Getter Setter Done - ");
            for(int i=0;i<s.length;i++){
                /*System.out.println("listObject usting toString"+list.get(i).toString());
                String[] split = list.get(i).toString().split(",");
                System.out.println(split[0].substring(1));
                System.out.println(split[1].substring(0,split[1].length()-1));
*/
                labels.add(s[i].getCity());
                entries.add(new Entry(s[i].getNumbers(),i));

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
