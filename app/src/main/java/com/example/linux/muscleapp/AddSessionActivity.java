package com.example.linux.muscleapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.model.Date;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class add a training session with it's properties
 */
public class AddSessionActivity extends AppCompatActivity {

    @BindView(R.id.fbtAddExcersice) FloatingActionButton fbtExcersice;
    @BindView(R.id.imgAddSessionDate) ImageView imgDate;
    @BindView(R.id.txvSeeSessionDates) TextView txvSessionDates;
    @BindView(R.id.btnCreateSession) Button btnCreate;
    DatePickerListener dplistener = new DatePickerListener();
    int contador = 0;



    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        ButterKnife.bind(this);


        date = new Date();
        date.GetDate();



        fbtExcersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddSessionActivity.this,AddExcersiceActivity.class);
                startActivity(intent);
            }
        });



        //Open DatePickerDialog when you click on the calendar image
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create datepicker (context activity, style, listener,year, month,day)
                DatePickerDialog dpdDate = new DatePickerDialog(AddSessionActivity.this,R.style.DatePicker,dplistener,date.getYear(),date.getMonth(),date.getDay());
                //Set max limit on current date
                dpdDate.show();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    class DatePickerListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        }


    }

}
