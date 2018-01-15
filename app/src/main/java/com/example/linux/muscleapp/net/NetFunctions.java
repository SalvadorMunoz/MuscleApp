package com.example.linux.muscleapp.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.session.SessionActivity;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by linux on 15/01/18.
 */

public class NetFunctions {
    private final static String WEB= "https://muscleapp.club/videoupload.php";
    private Context context;

    public NetFunctions(Context context) {
        this.context = context;
    }

    public void uploadVideo(File video) {
        final ProgressDialog progreso = new ProgressDialog(context);

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
                            RestClient.cancelRequests(context.getApplicationContext(), true);

                        }

                    });
                    progreso.show();
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    progreso.dismiss();
                    Toast.makeText(context, context.getResources().getString(R.string.upload_ok), Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
                    progreso.dismiss();
                    Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

    }
}
