package com.example.linux.muscleapp.ui.session.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.SeeSessionAdapter;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.presenter.SeeSessionPresenterImp;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeSessionFragment extends ListFragment implements SessionContract.SeeSessionView {
    public static final String TAG ="seesession";
    private Toolbar toolbar;
    private FloatingActionButton fbtDates;
    private SeeSessionAdapter adapter;
    private SessionContract.SeeSessionsPresenter presenter;
    private SeeSessionListener calllback;
    private Session tmp = null;


    public SeeSessionFragment() {
        // Required empty public constructor
    }


    public interface SeeSessionListener{
        void seeDates(int sessionId);
        void seeExcersice(Excersice excersice);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        calllback = (SeeSessionListener) activity;
    }
    public static SeeSessionFragment getInstance(Bundle bundle){
        SeeSessionFragment seeSessionFragment = new SeeSessionFragment();
        if(bundle != null)
            seeSessionFragment.setArguments(bundle);
        return  seeSessionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see_session,container,false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        fbtDates = (FloatingActionButton) view.findViewById(R.id.fbtDates);

        adapter = new SeeSessionAdapter(getActivity());
        presenter = new SeeSessionPresenterImp(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setAdapter(adapter);

        if(getArguments()!=null)
            tmp = getArguments().getParcelable("current");

        toolbar.setTitle(tmp.getName());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        presenter.getExcersices(tmp.getId());

        fbtDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calllback.seeDates(tmp.getId());
            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Excersice tmp = (Excersice) adapterView.getItemAtPosition(i);
                calllback.seeExcersice(tmp);
            }
        });
    }



    @Override
    public void fillExcersices(ArrayList<Excersice> excersices) {
        adapter.clear();
        adapter.addAll(excersices);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
    }
}
