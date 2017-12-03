package com.example.linux.muscleapp.ui.session.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.ExcersicesAdapter;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.presenter.AddSessionListExcersicesPresenter;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSessionFragment extends ListFragment implements SessionContract.AddSessionView{
    public static final String TAG ="addSession";

    private FloatingActionButton fbtExcersice;
    private Button btnCreate;
    private TextView txvAddDate,txvNumExcersices;
    private Toolbar toolbar;
    private User current;
    private AddSessionListener callback;
    private SessionContract.AddSessionPresenter presenter;


    private ExcersicesAdapter adapter;

    @Override
    public void fillExcersices(ArrayList<Excersice> excersices) {
        adapter.clear();
        adapter.addAll(excersices);
        txvNumExcersices.setText(String.valueOf(adapter.getCount())+" de "+SessionTmpDates.EXCERSICES_LIMIT);
    }

    public interface AddSessionListener{
        void goAddDate();
        void goAddExcersice();
    }

    public AddSessionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (AddSessionListener) activity;

    }

    public static AddSessionFragment newInstance(Bundle bundle){
        AddSessionFragment addSessionFragment = new AddSessionFragment();
        if(bundle != null)
            addSessionFragment.setArguments(bundle);
        return addSessionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_session,container,false);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        btnCreate = (Button) root.findViewById(R.id.btnCreateSession);
        txvAddDate = (TextView) root.findViewById(R.id.txvAddSessionDate);
        fbtExcersice = (FloatingActionButton) root.findViewById(R.id.fbtAddExcersice);
        txvNumExcersices = (TextView) root.findViewById(R.id.txvNumExcersices);

        presenter = new AddSessionListExcersicesPresenter(this);
        adapter = new ExcersicesAdapter(getActivity(),presenter);


        current = getArguments().getParcelable("currentUser");
        SessionTmpDates.initialize();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.add_session);

        getListView().setAdapter(adapter);

        presenter.getExcersices();



        fbtExcersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.goAddExcersice();
            }
        });

        txvAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.goAddDate();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
        SessionTmpDates.destroyDates();
    }
}
