package com.example.linux.muscleapp.ui.session;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.net.RestClient;
import com.example.linux.muscleapp.ui.dates.fragment.AddSessionDateFragment;
import com.example.linux.muscleapp.ui.excersice.fragment.AddExcersiceFragment;
import com.example.linux.muscleapp.ui.session.fragment.AddSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeeSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeedatesDialog;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.UriConverter;
import com.example.linux.muscleapp.ui.utils.ZipManager;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class SessionActivity extends AppCompatActivity implements AddSessionFragment.AddSessionListener, AddExcersiceFragment.AddExcersiceListener,SeeSessionFragment.SeeSessionListener{
    private AddSessionFragment addSessionFragment;
    private AddSessionDateFragment addSessionDateFragment;
    private AddExcersiceFragment addExcersiceFragment;
    private SeeSessionFragment seeSessionFragment;
    private SeedatesDialog seedatesDialog;

    private int currentUser;
    private static final int CAMERA_REQUEST =1;

    public final static String WEB= "https://muscleapp.club/videoupload.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        addSessionDateFragment = (AddSessionDateFragment) getSupportFragmentManager().findFragmentByTag(AddSessionDateFragment.TAG);
        if(addSessionDateFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addSessionDateFragment = AddSessionDateFragment.newInstance(null);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,addSessionDateFragment,AddSessionDateFragment.TAG).commit();
        }

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
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goRecordVideo(int currentUser) {
        this.currentUser = currentUser;
        new MaterialCamera(this).maxAllowedFileSize(1024 * 1024 * 5)
                .primaryColor(getResources().getColor(R.color.colorPrimary))
                .qualityProfile(MaterialCamera.QUALITY_480P).
                showPortraitWarning(false)
                .countdownMinutes(0.16f)
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
    public void seeDates(int sessionId) {
        seedatesDialog = (SeedatesDialog) getSupportFragmentManager().findFragmentByTag(SeedatesDialog.TAG);
        if(seedatesDialog == null){
            Bundle bundle = new Bundle();
            bundle.putInt("current",sessionId);
            seedatesDialog = SeedatesDialog.getInstance(bundle);
            seedatesDialog.show(getSupportFragmentManager(),SeedatesDialog.TAG);

        }
    }

    @Override
    public void seeExcersice(Excersice excersice) {

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
                    String path = getFilesDir().getPath()+"/VID_"+String.valueOf(currentUser)+String.valueOf(numtmp)+".zip";

                    Uri uri = data.getData();

                    ZipManager manager = new ZipManager();
                    manager.zip(UriConverter.getRealPathFromURI(this,uri),path);

                    File tmp = new File(path);
                    uploadVideo(tmp);
                }
                break;
        }
    }


    public void uploadVideo(File video) {
        final ProgressDialog progreso = new ProgressDialog(this);

        Boolean existe =true;
        RequestParams params = new  RequestParams();
        try
        {
            params.put("fileToUpload", video);
        }
        catch (FileNotFoundException e) {
            existe = false;
        }
        if(existe)
            RestClient.post(WEB, params, new TextHttpResponseHandler() {



                @Override
                public void onStart() {

                    progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progreso.setMessage("Conectando . . .");
                    ;
                    progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            RestClient.cancelRequests(getApplicationContext(), true);

                        }

                    });
                    progreso.show();
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    progreso.dismiss();
                    Toast.makeText(SessionActivity.this, getResources().getString(R.string.upload_ok), Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
                    progreso.dismiss();
                    Toast.makeText(SessionActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

    }
}
