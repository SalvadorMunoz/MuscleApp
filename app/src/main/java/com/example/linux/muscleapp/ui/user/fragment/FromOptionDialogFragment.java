package com.example.linux.muscleapp.ui.user.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linux.muscleapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FromOptionDialogFragment extends DialogFragment {
    public static final String TAG ="selectImage";
    private ConstraintLayout ctlCamera,ctlGallery;
    private ImageOptionListener callback;

    public interface ImageOptionListener{
        void openCamera();
        void openGallery();
    }

    public FromOptionDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (ImageOptionListener) activity;

    }

    public static FromOptionDialogFragment getInstance(Bundle bundle){
        FromOptionDialogFragment fromOptionDialogFragment = new FromOptionDialogFragment();
        if(bundle != null)
            fromOptionDialogFragment.setArguments(bundle);

        return fromOptionDialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_from_option_dialog,null);
        builder.setView(view);

        ctlCamera = (ConstraintLayout) view.findViewById(R.id.ctlCameraOption);
        ctlGallery = (ConstraintLayout) view.findViewById(R.id.ctlGalleryOption);

        ctlCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.openCamera();
                dismiss();
            }
        });

        ctlGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.openGallery();
                dismiss();
            }
        });
        return builder.create();
    }
}
