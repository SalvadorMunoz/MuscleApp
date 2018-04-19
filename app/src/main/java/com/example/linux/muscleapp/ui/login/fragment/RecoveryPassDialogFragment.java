package com.example.linux.muscleapp.ui.login.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.ui.login.contract.LoginContract;
import com.example.linux.muscleapp.ui.login.presenter.LoginPresenter;
import com.example.linux.muscleapp.ui.session.fragment.CheckPassDialog;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryPassDialogFragment extends DialogFragment implements LoginContract.RecoveryView{
    private EditText email;
    public static final String TAG ="recoverypass";
    private LoginContract.LoginPresenter presenter;
    private ProgressDialog progressDialog;
    private RecoveryDialogListener callback;

    public interface RecoveryDialogListener{
        void showError();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (RecoveryDialogListener) activity;
    }

    public static RecoveryPassDialogFragment getInstance(Bundle bundle){
        RecoveryPassDialogFragment recoveryPassDialogFragment = new RecoveryPassDialogFragment();
        if(bundle != null)
            recoveryPassDialogFragment.setArguments(bundle);

        return recoveryPassDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_recovery_pass_dialog,null);

        email = (EditText) root.findViewById(R.id.edtRecoveryPass);
        presenter = new LoginPresenter(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getResources().getString(R.string.autentication));

        builder.setTitle(getResources().getString(R.string.recovery_pass_title)).setMessage(getResources().getString(R.string.recovery_pass_message))
                .setPositiveButton(getResources().getString(R.string.accept), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.sendEmail(email.getText().toString());
                        dismiss();

                    }
                }).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        builder.setView(root);
        return builder.create();
    }



    @Override
    public void openDialog() {
        progressDialog.show();
    }

    @Override
    public void closeDialog(boolean res) {
         progressDialog.dismiss();
        if(!res)
            callback.showError();

    }

}
