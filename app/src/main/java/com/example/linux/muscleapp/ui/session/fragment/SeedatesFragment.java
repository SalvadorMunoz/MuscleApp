package com.example.linux.muscleapp.ui.session.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.presenter.SeeDatesPresenterImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeedatesFragment extends Fragment implements SessionContract.SeeDatesView{
    public static final String TAG = "seedates";
    private int session ;
    private CalendarView cldDates;
    private ConnectedDays connectedDays;
    private Set<Long> days;

    private SessionContract.SeeDatesPresenter presenter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static SeedatesFragment getInstance(Bundle bundle) {
        SeedatesFragment seedatesFragment = new SeedatesFragment();
        if (bundle != null)
            seedatesFragment.setArguments(bundle);

        return seedatesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_seedates, null);
        cldDates = (CalendarView) root.findViewById(R.id.cldDates);

        presenter = new SeeDatesPresenterImp(this);

        if (getArguments() != null) {
            session = getArguments().getInt("current");
        }

        return root;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cldDates.setDayTextColor(getResources().getColor(android.R.color.primary_text_light));
        cldDates.setWeekendDayTextColor(getResources().getColor(android.R.color.primary_text_light));
        presenter.getDates(session);

    }

    @Override
    public void fillDates(ArrayList<SessionDate> dates) {

        days = new TreeSet<>();
        for (int i= 0; i < dates.size();i++){
            days.add(currentTime(dates.get(i)));
        }


        connectedDays = new ConnectedDays(days, getResources().getColor(R.color.colorPrimary));
        cldDates.addConnectedDays(connectedDays);

    }
    private long currentTime (SessionDate sessionDate){
        String tmp = String.format("%04d-%02d-%02d",sessionDate.getYear(),sessionDate.getMonth(),sessionDate.getDay() );
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(sdf.parse(tmp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.getTimeInMillis();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
        connectedDays = new ConnectedDays(days,getResources().getColor(android.R.color.primary_text_light));
        cldDates.addConnectedDays(connectedDays);
    }
}
