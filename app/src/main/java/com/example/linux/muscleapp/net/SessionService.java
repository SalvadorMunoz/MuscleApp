package com.example.linux.muscleapp.net;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionDatesRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.ui.session.interactor.AddSessionInteractor;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

import java.util.ArrayList;

/**
 * Created by linux on 19/05/18.
 */

public class SessionService extends IntentService {
    private Excersice tmpEx = null;
    private SessionDate tmpDat = null;
    private Session session;
    private ArrayList<Excersice> excersices;
    private ArrayList<SessionDate> dates;

    public SessionService(){super("sessionService");}

    public SessionService(String name) {
        super(name);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        session = intent.getParcelableExtra("current");
        excersices = intent.getParcelableArrayListExtra("excersices");
        dates = intent.getParcelableArrayListExtra("dates");

        Log.e("1", session.getName() );
        SessionsRepository.getInstace().add(session);

        int tmpId = SessionsRepository.getInstace().getIdFromSession(session);

        for(int i = 0; i < excersices.size(); i++){
            tmpEx = excersices.get(i);
            tmpEx.setSession(tmpId);
            ExcersiceRepository.getInstance().add(tmpEx);
        }

        for(int i = 0; i < dates.size();i++){
            tmpDat = dates.get(i);
            tmpDat.setSession(tmpId);
            SessionDatesRepository.getInstance().add(tmpDat);
        }





    }




}
