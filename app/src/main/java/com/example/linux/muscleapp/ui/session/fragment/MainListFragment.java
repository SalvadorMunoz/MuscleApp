package com.example.linux.muscleapp.ui.session.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.MainAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,SessionContract.MainView{
    public static  final String TAG = "main";
    private SwipeRefreshLayout swipeContainer;
    private FloatingActionButton fbtAdd;
    private Toolbar toolbar;
    private ArrayList<Session> sessions;
    private User current;
    private MainListListener callback;

    SessionContract.MainPresenter presenter;
    private RecyclerView recycler;

    MainAdapter adapter;

    public interface MainListListener{
        void goComments(User current, int idSession);
        void addSession(User current);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (MainListListener) activity;

    }

    public static MainListFragment newInstance(Bundle bundle){
        MainListFragment mainListFragment = new MainListFragment();
        if(bundle != null)
            mainListFragment.setArguments(bundle);
        return  mainListFragment;

    }

    public MainListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_list,container,false);

        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        swipeContainer = (SwipeRefreshLayout) root.findViewById(R.id.rcvSession);
        fbtAdd = (FloatingActionButton) root.findViewById(R.id.fbtAdd);
        recycler = (RecyclerView) root.findViewById(R.id.rcvMain);

        adapter = new MainAdapter(sessions,current, callback);
        setRetainInstance(true);

        sessions = new ArrayList<>();


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        fbtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.addSession(current);

            }
        });
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        presenter.getSessions();
        presenter.getCurrentUser();

        recycler.setAdapter(adapter);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new MainAdapter(sessions,current,callback);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.actionPerfil);
        item.setTitle(current.getName());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.actionAccountSettings:
                //intent = new Intent(MainActivity.this,SettingsActivity.class);
                break;
            case R.id.actionAboutUs:
                //intent = new Intent(MainActivity.this,AboutUsActivity.class);
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
    public void setPresenter(SessionContract.MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        presenter.onDestroy();
    }
}
