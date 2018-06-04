package com.example.linux.muscleapp.ui.session.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.Sha256Generator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckPassDialog extends DialogFragment {
    public static final String TAG ="checkpass";
    private Session tmp = null;
    private EditText edtPass;
    private CheckDialogListener callback;

    public interface CheckDialogListener{
        void seeSession(Session session,int mode);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (CheckDialogListener) activity;
    }

    public static CheckPassDialog getInstance(Bundle bundle){
        CheckPassDialog checkPassDialog = new CheckPassDialog();
        if(bundle != null)
            checkPassDialog.setArguments(bundle);

        return checkPassDialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_check_pass_dialog,null);

        edtPass = (EditText) root.findViewById(R.id.edtCheckPass);


        if(getArguments() != null){
            tmp = getArguments().getParcelable("current");
        }

        builder.setTitle(getResources().getString(R.string.check_pass_dialog_title)).setMessage(getResources().getString(R.string.check_pass_dialog_message)+" "+tmp.getName())
                .setPositiveButton(getResources().getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String pass = Sha256Generator.bin2hex(Sha256Generator.getHash(edtPass.getText().toString()));
                if(tmp.getPass().equalsIgnoreCase(pass)){
                    callback.seeSession(tmp, GlobalVariables.OPEN_SEE);
                    dismiss();
                }else{
                    if(GlobalVariables.fromMain)
                        Snackbar.make(getActivity().findViewById(R.id.ctlMain),getResources().getString(R.string.err_invalid_pass),Snackbar.LENGTH_LONG).show();
                    else
                        Snackbar.make(getActivity().findViewById(R.id.userContent),getResources().getString(R.string.err_invalid_pass),Snackbar.LENGTH_LONG).show();

                    dismiss();
                }
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
}
