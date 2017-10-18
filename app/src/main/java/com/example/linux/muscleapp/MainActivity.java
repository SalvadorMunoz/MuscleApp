package com.example.linux.muscleapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is the main activity has got the sessions list, navigation drawer, add session button...
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    //Create and inflate container
    @BindView(R.id.srlContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.hola) TextView hola;
    @BindView(R.id.fbtVideo) FloatingActionButton fbtVideo;

    //Camera intent id and camera limit
    private static final int CAMERA_ID =1;
    private static final int VIDEO_LIMIT=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //Add listener
        swipeContainer.setOnRefreshListener(this);
        //Change swipe load color
        swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        fbtVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create camera intent and set limit
                Intent cameraintent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                cameraintent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,VIDEO_LIMIT);
                //Launch camera
                startActivityForResult(cameraintent,CAMERA_ID);
            }
        });

    }

    //Change tag when swipe activity refresh
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hola.setText("Refrescado");
                swipeContainer.setRefreshing(false);
            }
        },2000);

    }

    //See the results of callbacks intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check the activity result
        switch (requestCode){
            case CAMERA_ID:
                if(resultCode == Dialog.BUTTON_POSITIVE)
                    hola.setText("Video grabado...");
                break;
        }


    }
}
