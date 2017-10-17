package com.example.linux.muscleapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a sign up window with user's information
 */

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.imgSignUpDate) ImageView imgDate;
    @BindView(R.id.txvRes) TextView res;
    @BindView(R.id.btnSignUpCreateUser) Button btnCreate;

    private int day,month,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        GetDate();
        ButterKnife.bind(this);
        //Listener for DaterPickerDialog
        final DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //Show date selected
                res.setText(String.format("%04d-%02d-%02d",i,i1,i2));
            }
        };

        //Open DatePickerDialog when you click on the calendar image
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create datepicker (context activity, style, listener,year, month,day)
                DatePickerDialog dpdDate = new DatePickerDialog(SignUpActivity.this,R.style.DatePicker,dateSetListener,year,month,day);
                //Set max limit on current date
                dpdDate.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

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
    //Get current date
    private void GetDate(){
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);
    }


}
