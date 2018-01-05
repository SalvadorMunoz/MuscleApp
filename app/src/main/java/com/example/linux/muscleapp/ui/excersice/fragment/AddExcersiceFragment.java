package com.example.linux.muscleapp.ui.excersice.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.net.RestClient;
import com.example.linux.muscleapp.ui.excersice.contract.ExcersiceContract;
import com.example.linux.muscleapp.ui.excersice.presenter.ExcersicePresenter;
import com.example.linux.muscleapp.ui.session.fragment.AddSessionFragment;
import com.example.linux.muscleapp.ui.utils.CameraConfig;
import com.example.linux.muscleapp.ui.utils.UriConverter;
import com.example.linux.muscleapp.ui.utils.ZipManager;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.internal.Utils;
import cz.msebera.android.httpclient.Header;


public class AddExcersiceFragment extends Fragment implements ExcersiceContract.AddExcersiceView{
    public static final  String TAG = "addExcersice";
    public final static String WEB= "https://muscleapp.club/videoupload.php";


    private Button btnCreate;
    private TextInputLayout tilName, tilMuscle;
    private EditText edtName, edtMuscle;
    private FloatingActionButton fbtVideo;
    private NumberPicker time,series,repetitions;
    private Toolbar toolbar;
    private Spinner type;
    private ExcersiceContract.AddExcersicePresenter presenter;
    private AddExcersiceListener callback;

    private static final int LIMIT = 30;

    //Camera intent id and camera limit
    private static final int CAMERA_REQUEST =1;
    private static final int VIDEO_LIMIT=5;
    private User current;
    public interface AddExcersiceListener{
        void goBack();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (AddExcersiceListener) activity;
    }

    public static AddExcersiceFragment newInstance(Bundle bundle){
        AddExcersiceFragment addExcersiceFragment = new AddExcersiceFragment();
        if(bundle != null)
            addExcersiceFragment.setArguments(bundle);
        return addExcersiceFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_excersice,container,false);

        btnCreate = (Button) root.findViewById(R.id.btnCreateExcersice);
        fbtVideo = (FloatingActionButton) root.findViewById(R.id.fbtAddVideo);
        time = (NumberPicker) root.findViewById(R.id.nbpTime);
        series = (NumberPicker) root.findViewById(R.id.nbpSeries);
        repetitions = (NumberPicker) root.findViewById(R.id.nbpRepetitions);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        type = (Spinner) root.findViewById(R.id.spnType);
        tilName = (TextInputLayout) root.findViewById(R.id.tilAddExcersiceName);
        tilMuscle = (TextInputLayout) root.findViewById(R.id.tilAddExcersiceMuscle);
        edtName = (EditText) root.findViewById(R.id.edtAddExcersiceName);
        edtMuscle = (EditText) root.findViewById(R.id.edtAddExcersiceMuscle);


        presenter = new ExcersicePresenter(this);
        current = getArguments().getParcelable("currentUser");


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.add_excersice_title);
        setNumberPickerLimits();

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtMuscle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilMuscle.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addExcersice(0,0,edtName.getText().toString(),edtMuscle.getText().toString(),"",type.getSelectedItem().toString(),series.getValue(),repetitions.getValue(),time.getValue());
            }
        });
        fbtVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create camera intent and set limit
                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,VIDEO_LIMIT);
                CameraConfig.setResolution();
                if(cameraIntent.resolveActivity(getActivity().getPackageManager()) != null)
                    startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });
    }
    private  void setNumberPickerLimits(){
        time.setMaxValue(LIMIT);
        repetitions.setMaxValue(LIMIT);
        series.setMaxValue(LIMIT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check the activity result
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == Dialog.BUTTON_POSITIVE){
                    long numtmp = AppPreferencesHelper.newInstance().getNumVideo()+1;
                    AppPreferencesHelper.newInstance().setNumVideo(numtmp);
                    String path = getContext().getFilesDir().getPath()+"/"+String.valueOf(current.getId())+String.valueOf(numtmp)+".zip";

                    Uri uri = data.getData();

                    ZipManager manager = new ZipManager();
                    manager.zip(UriConverter.getRealPathFromURI(getActivity(),uri),path);

                    File tmp = new File(path);
                    uploadVideo(tmp);
                }
                    break;
        }
    }



    private void uploadVideo(File video) {
        final ProgressDialog progreso = new ProgressDialog(getContext());

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
                            RestClient.cancelRequests(getActivity().getApplicationContext(), true);

                        }

                    });
                    progreso.show();
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    progreso.dismiss();
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
                    progreso.dismiss();
                    Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

    }





    @Override
    public void setOnEmptyName() {
        tilName.setError(getResources().getString(R.string.err_emptyname));
    }

    @Override
    public void setOnEmptyMuscle() {
        tilMuscle.setError(getResources().getString(R.string.err_emptymuscle));
    }

    @Override
    public void onSuccess() {
        callback.goBack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }
}
