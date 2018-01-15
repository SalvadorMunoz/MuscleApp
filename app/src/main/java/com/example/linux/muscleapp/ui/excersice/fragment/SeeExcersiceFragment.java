package com.example.linux.muscleapp.ui.excersice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.ExcersiceAdapter;
import com.example.linux.muscleapp.data.db.pojo.Excersice;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeExcersiceFragment extends Fragment {
    public static final  String TAG = "seeexcersice";
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<String> datas;

    private Excersice tmp;
    private ExcersiceAdapter adapter;

    public SeeExcersiceFragment() {
        // Required empty public constructor
    }
    public static SeeExcersiceFragment newInstance(Bundle bundle){
        SeeExcersiceFragment seeExcersiceFragment = new SeeExcersiceFragment();
        if(bundle != null)
            seeExcersiceFragment.setArguments(bundle);
        return seeExcersiceFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_see_excersice,container,false);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) root.findViewById(R.id.rcvExcersice);

        datas = new ArrayList<>();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() !=null)
            tmp = getArguments().getParcelable("current");

        toolbar.setTitle(tmp.getName());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        datas.add(getActivity().getResources().getString(R.string.muscle)+": "+tmp.getMuscle());
        if(tmp.getSeries() !=0)
            datas.add(getActivity().getResources().getString(R.string.series)+": "+String.valueOf(tmp.getSeries()));
        if(tmp.getRepetitions() != 0)
            datas.add(String.valueOf(getActivity().getResources().getString(R.string.repetitions)+": "+tmp.getRepetitions()));
        if(tmp.getTime() !=0)
            datas.add(String.valueOf(getActivity().getResources().getString(R.string.time)+": "+tmp.getTime())+" "+tmp.getTypeTime());

        adapter = new ExcersiceAdapter(datas);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
