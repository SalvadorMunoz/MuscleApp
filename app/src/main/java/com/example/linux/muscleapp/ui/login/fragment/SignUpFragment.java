package com.example.linux.muscleapp.ui.login.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.login.contract.LoginContract;

import java.util.Calendar;

import butterknife.BindView;


public class SignUpFragment extends Fragment implements LoginContract.SignUpView {
    public static final  String TAG="signup";
    private Toolbar toolbar;
    private ImageView imgDate;
    private TextView res;
    private Button btnCreate;
    private EditText edtEmail, edtPass, edtName,edtResidence;
    private TextInputLayout tilEmail, tilPass, tilName,tilResidence;
    private LoginContract.SignupPresenter presenter ;
    private SignupListener callback;

    public interface SignupListener{
        void goLogin();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callback = (SignupListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up,container,false);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sign_up);

        imgDate = (ImageView) root.findViewById(R.id.imgSignUpDate);
        res = (TextView) root.findViewById(R.id.txvRes);
        btnCreate = (Button) root.findViewById(R.id.btnSignUpCreateUser);

        tilEmail = (TextInputLayout) root.findViewById(R.id.tilSignupEmail);
        tilPass = (TextInputLayout) root.findViewById(R.id.tilSignupPass);
        tilName = (TextInputLayout) root.findViewById(R.id.tilSignupName);
        tilResidence = (TextInputLayout) root.findViewById(R.id.tilSignupResidence);

        edtEmail = (EditText) root.findViewById(R.id.edtSingupEmail);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtPass = (EditText) root.findViewById(R.id.edtSingupPass);
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtName = (EditText) root.findViewById(R.id.edtSingupName);
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

        edtResidence = (EditText) root.findViewById(R.id.edtSingupResidence);
        edtResidence.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilResidence.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //Show date selected
                res.setText(String.format("%02d-%02d-%04d",i2,(i1+1),i));
            }
        };

        //Open DatePickerDialog when you click on the calendar image
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res.setText("");
                res.setTextColor(Color.BLACK);

                //create datepicker (context activity, style, listener,year, month,day)
                DatePickerDialog dpdDate = new DatePickerDialog(getActivity(),
                        R.style.DatePicker,dateSetListener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));                //Set max limit on current date
                dpdDate.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

                dpdDate.show();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.add(edtEmail.getText().toString(),edtPass.getText().toString(),edtName.getText().toString(),edtResidence.getText().toString(),res.getText().toString());
            }
        });

        return root;
    }

    public  static SignUpFragment newInstance(Bundle b){
        SignUpFragment signUpFragment = new SignUpFragment();
        if(b != null)
            signUpFragment.setArguments(b);
        return signUpFragment;
    }

    @Override
    public void setPresenter(LoginContract.SignupPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setEmptyEmail() {
        tilEmail.setError(getResources().getString(R.string.err_emptyemail));
    }

    @Override
    public void setEmailExists() {
        Snackbar.make(getActivity().findViewById(android.R.id.content),getResources().getString(R.string.err_emailexists),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setEmptyPass() {
        tilPass.setError(getResources().getString(R.string.err_emptypass));
    }

    @Override
    public void setEmptyResidence() {
        tilResidence.setError(getResources().getString(R.string.err_emptyresidence));
    }

    @Override
    public void setEmptyBornDate() {
        res.setTextColor(Color.RED);
        res.setText(getResources().getString(R.string.err_emptyborndate));
    }

    @Override
    public void setEmptyName() {
        tilName.setError(getResources().getString(R.string.err_emptyname));
    }

    @Override
    public void setErrorEmail() {
        tilEmail.setError(getResources().getString(R.string.err_invalid_format));
    }

    @Override
    public void setErrorPass() {
        tilPass.setError(getResources().getString(R.string.err_invalid_pass_format));
    }

    @Override
    public void goLogin() {
        callback.goLogin();
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
