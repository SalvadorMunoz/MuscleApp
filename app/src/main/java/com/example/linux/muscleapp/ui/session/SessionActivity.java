package com.example.linux.muscleapp.ui.session;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialcamera.MaterialCamera;
import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.net.NetFunctions;
import com.example.linux.muscleapp.net.UploadService;
import com.example.linux.muscleapp.ui.dates.fragment.AddSessionDateFragment;
import com.example.linux.muscleapp.ui.excersice.VideoPlayerActivity;
import com.example.linux.muscleapp.ui.excersice.fragment.AddExcersiceFragment;
import com.example.linux.muscleapp.ui.excersice.fragment.SeeExcersiceFragment;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.fragment.AddSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeeSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeedatesFragment;
import com.example.linux.muscleapp.ui.session.presenter.AddDatesPresenterImp;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;
import com.example.linux.muscleapp.ui.utils.UriConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SessionActivity extends AppCompatActivity implements AddSessionFragment.AddSessionListener, AddExcersiceFragment.AddExcersiceListener,SeeSessionFragment.SeeSessionListener,SeeExcersiceFragment.SeeExcersiceListener{
    private AddSessionFragment addSessionFragment;
    private AddSessionDateFragment addSessionDateFragment;
    private AddExcersiceFragment addExcersiceFragment;
    private SeeSessionFragment seeSessionFragment;
    private SeedatesFragment seedatesFragment;
    private SeeExcersiceFragment seeExcersiceFragment;
    private SessionContract.AddDatesPresenter addDatesPresenter;

    private int currentUser;
    private static final int CAMERA_REQUEST =1;

    private NetFunctions netFunctions;
    private File tmp;
    private String path;
    private String webStorage = "https://muscleapp.club/videos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netFunctions = new NetFunctions(this);
        switch (getIntent().getIntExtra("mode",-1)){
            case GlobalVariables.OPEN_ADD:
                User user = getIntent().getParcelableExtra("user");
                openAddSession(user);
                break;
            case GlobalVariables.OPEN_SEE:
                Session session = getIntent().getParcelableExtra("current");
                openSeeSession(session);
                break;
        }


    }

    @Override
    public void goAddDate() {
        SessionTmpDates.clearDates();
        CalendarDialog calendarDialog = new CalendarDialog(this, new OnDaysSelectionListener() {
            @Override
            public void onDaysSelected(List<Day> selectedDays) {
                ArrayList<Calendar> dates = new ArrayList<>();
                for (int i = 0; i< selectedDays.size();i++){
                    dates.add(selectedDays.get(i).getCalendar());
                }
                addDatesPresenter  = new AddDatesPresenterImp();
                addDatesPresenter.addDate(dates);


            }

        });

//        calendarDialog.setSelectedDayBackgroundColor(getResources().getColor(R.color.colorAccent));
        calendarDialog.show();

    }

    @Override
    public void goAddExcersice(User current) {
        addExcersiceFragment = (AddExcersiceFragment) getSupportFragmentManager().findFragmentByTag(AddExcersiceFragment.TAG);
        if(addExcersiceFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("currentUser", current);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addExcersiceFragment = AddExcersiceFragment.newInstance(bundle);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,addExcersiceFragment,AddExcersiceFragment.TAG).commit();
        }
    }

    @Override
    public void goMain() {
        finish();
    }



    @Override
    public void goBack(int currentExcersice) {
        if(tmp != null) {
            String currrentStorage;
            Intent service = new Intent(SessionActivity.this,UploadService.class);
            service.putExtra("currentVideo",tmp);
            String [] parts = path.split("/");
            currrentStorage = webStorage+parts[parts.length-1];
            SessionTmpDates.setUrlVideo(currentExcersice,currrentStorage);
            startService(service);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goRecordVideo(int currentUser) {
        this.currentUser = currentUser;
        File saveFolder = new File(Environment.getExternalStorageDirectory(), "muscleapp");
        new MaterialCamera(this).maxAllowedFileSize(1024 * 1024 * 5)
                .primaryColor(getResources().getColor(R.color.colorPrimary))
                .saveDir(saveFolder)
                .qualityProfile(MaterialCamera.QUALITY_480P).
                showPortraitWarning(false)
                .countdownMinutes(0.17f)
                .start(CAMERA_REQUEST);
    }

    void openAddSession(User user){
        addSessionFragment = (AddSessionFragment) getSupportFragmentManager().findFragmentByTag(AddSessionFragment.TAG);
        if(addSessionFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("currentUser",user);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addSessionFragment = AddSessionFragment.newInstance(bundle);
            transaction.add(android.R.id.content,addSessionFragment,AddSessionFragment.TAG).commit();
        }
    }
    void openSeeSession(Session session){
        seeSessionFragment = (SeeSessionFragment) getSupportFragmentManager().findFragmentByTag(SeeSessionFragment.TAG);
        if(addExcersiceFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current", session);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            seeSessionFragment = SeeSessionFragment.getInstance(bundle);
            transaction.replace(android.R.id.content,seeSessionFragment,SeeSessionFragment.TAG).commit();
        }

    }

    @Override
    public void seeDates(ArrayList<SessionDate> sessionDates) {
        seedatesFragment = (SeedatesFragment) getSupportFragmentManager().findFragmentByTag(SeedatesFragment.TAG);
        if(seedatesFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("current",sessionDates);
            seedatesFragment = SeedatesFragment.getInstance(bundle);
            transaction.addToBackStack(null);

            transaction.replace(android.R.id.content,seedatesFragment, SeedatesFragment.TAG).commit();

        }
    }

    @Override
    public void seeExcersice(Excersice excersice) {
        seeExcersiceFragment = (SeeExcersiceFragment) getSupportFragmentManager().findFragmentByTag(SeeExcersiceFragment.TAG);
        if(seeExcersiceFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current", excersice);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            seeExcersiceFragment = SeeExcersiceFragment.newInstance(bundle);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,seeExcersiceFragment,SeeExcersiceFragment.TAG).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check the activity result
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == Dialog.BUTTON_POSITIVE){
                    long numtmp = AppPreferencesHelper.newInstance().getNumVideo()+1;
                    AppPreferencesHelper.newInstance().setNumVideo(numtmp);
                    Uri uri = data.getData();

                    path =UriConverter.getRealPathFromURI(this,uri);

                    tmp = new File(path);
                }
                break;
        }
    }



    @Override
    public void goVideoPlayer(String url) {
        Intent intent = new Intent(SessionActivity.this, VideoPlayerActivity.class);
        intent.putExtra("video",url);
        startActivity(intent);
    }
}
