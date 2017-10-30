package com.example.linux.muscleapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.linux.muscleapp.adapters.MainAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 3.0
 *
 * This class is the main activity has got the sessions list, navigation drawer, add session button...
 */
public class MainActivity extends AppCompatActivity   implements SwipeRefreshLayout.OnRefreshListener{
    //Create and inflate container
    @BindView(R.id.srlContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.fbtAdd) FloatingActionButton fbtAdd;

    //Recycler view elements
    MainAdapter adapter;
    @BindView(R.id.rcvMain)RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //set property to paint the items
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //create and set recyclerview's adapter
        adapter = new MainAdapter();
        recycler.setAdapter(adapter);
        //Add listener
        swipeContainer.setOnRefreshListener(this);
        //Change swipe load color
        swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        fbtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddSessionActivity.class);
                startActivity(intent);

            }
        });

    }


    //Change tag when swipe activity refresh
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeContainer.setRefreshing(false);
            }
        },2000);

    }


}
