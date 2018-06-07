package com.example.linux.muscleapp.ui.excersice.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.excersice.contract.ExcersiceContract;
import com.example.linux.muscleapp.ui.excersice.presenter.ExcersicePresenter;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;


public class AddExcersiceFragment extends Fragment implements ExcersiceContract.AddExcersiceView{
    public static final  String TAG = "addExcersice";


    private Button btnCreate;
    private TextInputLayout tilName, tilMuscle;
    private EditText edtName, edtMuscle;
    private FloatingActionButton fbtVideo;
    private NumberPicker time,series,repetitions;
    private Toolbar toolbar;
    private Spinner type;
    private ExcersiceContract.AddExcersicePresenter presenter;
    private AddExcersiceListener callback;
    private int excersiceId ;

    private static final int LIMIT = 30;

    private User current;
    public interface AddExcersiceListener{
        void goBack(int currentExcersice);
        void goRecordVideo(int  curretUser);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        presenter.getCurrentUser(MuscleAppApplication.getContex().getAppPreferencesHelper().getCurrentUser());

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.add_excersice_title);
        setNumberPickerLimits();
        if(getArguments() !=  null){
            excersiceId = getArguments().getInt("excericeId");
        }

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

                presenter.addExcersice(new Excersice(excersiceId,0,edtName.getText().toString(),edtMuscle.getText().toString(),"",type.getSelectedItem().toString(),series.getValue(),repetitions.getValue(),time.getValue()));
            }
        });
        fbtVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
            }
        });
    }
    private  void setNumberPickerLimits(){
        time.setMaxValue(LIMIT);
        repetitions.setMaxValue(LIMIT);
        series.setMaxValue(LIMIT);
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
    public void onSuccess(int id) {
        callback.goBack(id);
    }

    @Override
    public void setCurrentUser(User user) {
         current = user;
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
    int permission = 20;
    boolean permissionGranted = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 20 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permission++;
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, permission);
            }else{
                permissionGranted = true;
            }

        }

        if (requestCode == 21 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                permission++;
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission);
            }else{
                permissionGranted = true;
            }


        }

        if (requestCode == 22 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                permission++;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, permission);
            }else{
                permissionGranted = true;
            }

        }
        if (requestCode == 23 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGranted = true;

        }
        if(permissionGranted)
            callback.goRecordVideo(current.getId());
    }
    private  void checkPermissions(){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            requestPermissions( new String[]{Manifest.permission.CAMERA}, permission);
        else
            callback.goRecordVideo(current.getId());



    }
}
