package com.example.linux.muscleapp.ui.dates;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.SessionDatesAdapter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDateActivity extends AppCompatActivity {
    @BindView(R.id.fbtAddDate) FloatingActionButton addDate;

    DatePickerListener dplistener = new DatePickerListener();

    @BindView(R.id.lstDates)ListView listView;
    SessionDatesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        ButterKnife.bind(this);

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create datepicker (context activity, style, listener,year, month,day)
                DatePickerDialog dpdDate = new DatePickerDialog(AddDateActivity.this,
                        R.style.DatePicker,dplistener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                dpdDate.show();
            }
        });

        adapter = new SessionDatesAdapter(this);
        listView.setAdapter(adapter);
    }

    class DatePickerListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        }


    }
}
