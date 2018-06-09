package com.example.linux.muscleapp.ui.today.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.FavouritesAdapter;
import com.example.linux.muscleapp.adapters.SessionListAdapter;
import com.example.linux.muscleapp.adapters.UserProfileAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.favourite.fragment.FavouritesFragment;
import com.example.linux.muscleapp.ui.today.contract.TodaySessionContract;
import com.example.linux.muscleapp.ui.today.presenter.TodaySessionsPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends ListFragment implements TodaySessionContract.View{
    public static final String TAG ="todaySessions";
    private User current;
    private TodaySessionContract.Presenter presenter;
    private SessionListAdapter adapter;
    private FavouritesFragment.SeeDetailsListener callback;
    private ProgressDialog progressDialog;
    private TextView no_Sessions;


    public TodayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (FavouritesFragment.SeeDetailsListener) activity;
    }

    public static TodayFragment newInstance(Bundle bundle){
        TodayFragment todayFragment = new TodayFragment();
        if(bundle != null)
            todayFragment.setArguments(bundle);
        return  todayFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        presenter = new TodaySessionsPresenter(this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getResources().getString(R.string.downloading));

        no_Sessions = (TextView) view.findViewById(R.id.txvNoToday);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.action_today));
        if(getArguments()!=null)
            current = getArguments().getParcelable("current");
    }

    @Override
    public void openDialog() {
        progressDialog.show();
    }

    @Override
    public void fillSessions(ArrayList<Session> today,ArrayList<User> usernames,ArrayList<Boolean> favourites) {

        if(today.size() ==0){
            getListView().setVisibility(View.GONE);
            no_Sessions.setVisibility(View.VISIBLE);
        }else {
            getListView().setVisibility(View.VISIBLE);
            no_Sessions.setVisibility(View.GONE);
            adapter = new SessionListAdapter(getContext(), current, usernames, today, presenter, callback, favourites);

            getListView().setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTodaySession(current.getId());

    }

    @Override
    public void closeDialog() {
        progressDialog.dismiss();
    }
}
