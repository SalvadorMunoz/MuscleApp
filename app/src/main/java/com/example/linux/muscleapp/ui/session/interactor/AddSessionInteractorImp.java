package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionDatesRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

import java.util.Date;

/**
 * Created by linux on 3/12/17.
 */

public class AddSessionInteractorImp implements AddSessionInteractor{


    @Override
    public void addSession(String name, String pass, int user, OnAddSessionFinish onAddSessionFinish) {
        Excersice tmpEx = null;
        SessionDate tmpDat = null;
        if(name.isEmpty())
            onAddSessionFinish.onEmptyName();
        else {
            Session tmp = new Session(SessionsRepository.getInstace().getLastId() + 1, user, R.drawable.no_photo, name, pass, new Date());
            SessionsRepository.getInstace().add(tmp);
            for(int i = 0; i < SessionTmpDates.getExcersices().size();i++){
                tmpEx = SessionTmpDates.getExcersices().get(i);
                tmpEx.setSession(tmp.getId());
                ExcersiceRepository.getInstance().add(tmpEx);
            }
            for(int i = 0; i < SessionTmpDates.getDates().size();i++){
                tmpDat = SessionTmpDates.getDates().get(i);
                tmpDat.setSessionId(tmp.getId());
                tmpDat.setId(SessionDatesRepository.getInstance().getLastId()+1);
                SessionDatesRepository.getInstance().add(tmpDat);
            }
            onAddSessionFinish.onSuccess();
        }
    }

    @Override
    public void getExcersices(OnAddSessionFinish onAddSessionFinish) {
        onAddSessionFinish.onLoadExcersices(SessionTmpDates.getExcersices());
    }

    @Override
    public void deleteExcersice(int position, OnAddSessionFinish onAddSessionFinish) {
        SessionTmpDates.deleteExcersice(position);
        onAddSessionFinish.onLoadExcersices(SessionTmpDates.getExcersices());
    }
}
