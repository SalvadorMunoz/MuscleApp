package com.example.linux.muscleapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.linux.muscleapp.adapters.ExcersicesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 2.0
 *
 * This class add a training session with it's properties
 */
public class AddSessionActivity extends AppCompatActivity {

    @BindView(R.id.fbtAddExcersice) FloatingActionButton fbtExcersice;
    @BindView(R.id.btnCreateSession) Button btnCreate;
    @BindView(R.id.txvAddSessionDate) TextView txvAddDate;
    @BindView(R.id.lstExcersices) ListView listView;

    ExcersicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        ButterKnife.bind(this);

        adapter = new ExcersicesAdapter(this);
        listView.setAdapter(adapter);

        fbtExcersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddSessionActivity.this,AddExcersiceActivity.class);
                startActivity(intent);
            }
        });

        txvAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSessionActivity.this,AddDateActivity.class);
                startActivity(intent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }




}
