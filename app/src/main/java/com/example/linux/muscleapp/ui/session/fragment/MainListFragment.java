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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.MainAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.prefs.AppPreferences;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.presenter.MainListPresenterImp;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;
import com.pkmmte.view.CircularImageView;

import java.lang.reflect.Array;
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
    private ArrayList<User> usernames;
    private ArrayList<Boolean> favourites;


    SessionContract.MainPresenter presenter;
    private RecyclerView recycler;

    MainAdapter adapter;

    public interface MainListListener{
        void goComments(User current, int idSession,ArrayList<User> usernames);
        void addSession(User current, int mode);
        void checkSessionPassword(Session session);
        void seeSession(Session session,int mode);

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

        setRetainInstance(true);

        sessions = new ArrayList<>();
        presenter = new MainListPresenterImp(this);

        Log.d("ASS", MuscleAppApplication.getContex().getAppPreferencesHelper().getCurrentUser());


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
                callback.addSession(current, GlobalVariables.OPEN_ADD);

            }
        });
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recycler.setAdapter(adapter);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCurrentUser(MuscleAppApplication.getContex().getAppPreferencesHelper().getCurrentUser());
    }

    private void getSessions(){
        presenter.getSessions(current.getId());

    }
    //Change tag when swipe activity refresh
    @Override
    public void onRefresh() {
        getSessions();
    }

    @Override
    public void fillSessions(ArrayList<Session> sessions, ArrayList<User> usernames,ArrayList<Boolean> favourites,ArrayList<Session>favSessions) {
        this.sessions = sessions;
        this.usernames = usernames;
        this.favourites = favourites;
    }

    @Override
    public void getCurrentUser(User user) {
        current = user;
        getSessions();

    }

    @Override
    public void openRefreshing() {
        swipeContainer.setRefreshing(true);
    }

    @Override
    public void closeRefreshing() {
        swipeContainer.setRefreshing(false);
        adapter = new MainAdapter(sessions,usernames,current,callback,this,favourites);
        recycler.setAdapter(adapter);
    }

    @Override
    public void updateFavourite() {

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
