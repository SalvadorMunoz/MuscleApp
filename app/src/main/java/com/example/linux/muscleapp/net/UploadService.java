package com.example.linux.muscleapp.net;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by linux on 8/05/18.
 */

public class UploadService extends IntentService {
    private final static String WEB= "https://muscleapp.club/videoupload.php";

    public UploadService() {
        super("uploadService");
    }

    public UploadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("asd", "dentro del servicio");
        Boolean existe =true;
        RequestParams params = new  RequestParams();
        File video = (File) intent.getExtras().get("currentVideo");
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

                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
                    //Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

    }
}
