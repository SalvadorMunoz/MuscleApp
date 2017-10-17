package com.example.linux.muscleapp;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //Add listener
        swipeContainer.setOnRefreshListener(this);
        //Change swipe load color
        swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

    }


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
}
