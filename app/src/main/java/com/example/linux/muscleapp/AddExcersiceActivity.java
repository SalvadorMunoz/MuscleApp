package com.example.linux.muscleapp;

import android.app.Dialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 2.0
 * This class creates a excersice with it's properties and a possible video
 */

public class AddExcersiceActivity extends AppCompatActivity {
    @BindView(R.id.btnCreateExcersice) Button btnCreate;
    @BindView(R.id.fbtAddVideo) FloatingActionButton fbtVideo;
    @BindView(R.id.nbpTime) NumberPicker time;
    @BindView(R.id.nbpSeries) NumberPicker series;
    @BindView(R.id.nbpRepetitions) NumberPicker repetitions;
    @BindView(R.id.toolbar)Toolbar toolbar;

    @BindView(R.id.spnType) Spinner type;

    private static final int LIMIT = 30;

    //Camera intent id and camera limit
    private static final int CAMERA_REQUEST =1;
    private static final int VIDEO_LIMIT=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excersice);
        ButterKnife.bind(this);

        time.setMaxValue(LIMIT);
        repetitions.setMaxValue(LIMIT);
        series.setMaxValue(LIMIT);

        toolbar.setTitle(R.string.add_excersice_title);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fbtVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create camera intent and set limit
                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,VIDEO_LIMIT);
                if(cameraIntent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });


    }

    //See the results of callbacks intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check the activity result
        switch (requestCode){
            case CAMERA_REQUEST:
                if(resultCode == Dialog.BUTTON_POSITIVE)
                    // Do something
                    break;
        }


    }

}
