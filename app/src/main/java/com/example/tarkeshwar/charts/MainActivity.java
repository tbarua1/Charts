package com.example.tarkeshwar.charts;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.tarkeshwar.charts.R.id.graph_division_topup;
import static com.example.tarkeshwar.charts.R.id.graph_final_assessment_fs;
import static com.example.tarkeshwar.charts.R.id.graph_over_all;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button topUP, fS, topUPDevision, itestFSFinal, division, itessFSDivision, placements, topUPFinal,
            graphoverall, graphfinalassessmentfs,graphdivisiontopup;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topUP = (Button) findViewById(R.id.top_up);
        fS = (Button) findViewById(R.id.foundation_skills);
        topUPDevision = (Button) findViewById(R.id.top_up_division);
        itestFSFinal = (Button) findViewById(R.id.ites_fs_final);
        division = (Button) findViewById(R.id.division);
        itessFSDivision = (Button) findViewById(R.id.ites_fs_division);
        placements = (Button) findViewById(R.id.placements);
        topUPFinal = (Button) findViewById(R.id.top_up_final);
graphoverall= (Button) findViewById(graph_over_all);
        graphfinalassessmentfs= (Button) findViewById(graph_final_assessment_fs);
        graphdivisiontopup= (Button) findViewById(graph_division_topup);

        topUP.setBackgroundColor(Color.GREEN);
        topUP.setOnClickListener(this);
        fS.setOnClickListener(this);
        fS.setBackgroundColor(Color.GREEN);
        topUPDevision.setOnClickListener(this);
        topUPDevision.setBackgroundColor(Color.GREEN);
        itestFSFinal.setOnClickListener(this);
        itestFSFinal.setBackgroundColor(Color.GREEN);
        division.setOnClickListener(this);
        division.setBackgroundColor(Color.GREEN);
        itessFSDivision.setOnClickListener(this);
itessFSDivision.setBackgroundColor(Color.GREEN);
        placements.setOnClickListener(this);
        placements.setBackgroundColor(Color.GREEN);
        topUPFinal.setOnClickListener(this);
        topUPFinal.setBackgroundColor(Color.GREEN);
        graphoverall.setOnClickListener(this);
        graphoverall.setBackgroundColor(Color.GREEN);
        graphfinalassessmentfs.setOnClickListener(this);
        graphdivisiontopup.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_up:
                intent=new Intent(MainActivity.this,GraphTopup.class);
                startActivity(intent);
                break;
            case R.id.foundation_skills:
                intent=new Intent(MainActivity.this,FoundationSkills.class);
                startActivity(intent);
                break;
            case R.id.top_up_division:
                intent=new Intent(MainActivity.this,TOPUPDivision.class);
                startActivity(intent);
                break;
            case R.id.ites_fs_final:
                intent=new Intent(MainActivity.this,ITESFSFinal.class);
                startActivity(intent);
                break;
            case R.id.division:
                intent=new Intent(MainActivity.this,GraphDivisionFS.class);
                startActivity(intent);
                break;
            case R.id.ites_fs_division:
                intent=new Intent(MainActivity.this,GraphDivisionOverAll.class);
                startActivity(intent);
                break;
            case R.id.placements:
                intent=new Intent(MainActivity.this,GraphPlacement.class);
                startActivity(intent);
                break;
            case R.id.top_up_final:
                intent=new Intent(MainActivity.this,GraphFinalAssessmentTopup.class);
                startActivity(intent);
                break;
            //
            case graph_over_all:
                intent=new Intent(MainActivity.this,GraphOverall.class);
                startActivity(intent);
                break;
            case graph_final_assessment_fs:
                intent=new Intent(MainActivity.this,GraphFinalAssessmentFS.class);
                startActivity(intent);
                break;
            case graph_division_topup:
                intent=new Intent(MainActivity.this,GraphDivisionTopup.class);
                startActivity(intent);
                break;


        }
    }
}
