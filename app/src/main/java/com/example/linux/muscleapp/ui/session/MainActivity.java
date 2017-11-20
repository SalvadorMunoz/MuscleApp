package com.example.linux.muscleapp.ui.session;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.MainAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.about.AboutUsActivity;
import com.example.linux.muscleapp.ui.setting.SettingsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 4.0
 *
 * This class is the main activity has got the sessions list, navigation drawer, add session button...
 */
public class MainActivity extends AppCompatActivity   implements SwipeRefreshLayout.OnRefreshListener,MainView{
    //Create and inflate container
    @BindView(R.id.srlContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.fbtAdd) FloatingActionButton fbtAdd;
    @BindView(R.id.toolbar) Toolbar toolbar;
    ArrayList<Session> sessions;
    User current;

    MainPresenter presenter;

    //Recycler view elements
    MainAdapter adapter;
    @BindView(R.id.rcvMain)RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessions = new ArrayList<>();

        presenter = new MainPresenterImp(this);
        presenter.getSessions();
        presenter.getCurrentUser();

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //set property to paint the items
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //create and set recyclerview's adapterLinearLayoutManager
        adapter = new MainAdapter(sessions,current);
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

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new MainAdapter(sessions,current);
        recycler.setAdapter(adapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.actionPerfil);
        item.setTitle(current.getName());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.actionAccountSettings:
                intent = new Intent(MainActivity.this,SettingsActivity.class);
                break;
            case R.id.actionAboutUs:
                intent = new Intent(MainActivity.this,AboutUsActivity.class);
                break;
        }
        if(intent != null)
            startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public void getCurrentUser(User user) {
        current = user;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
