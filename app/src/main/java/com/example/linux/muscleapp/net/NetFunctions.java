package com.example.linux.muscleapp.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Environment;
import android.widget.Toast;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.session.SessionActivity;
import com.example.linux.muscleapp.ui.utils.ZipManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable t) {
                    Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

    }
    public void downloadVideo(String url){
        final ProgressDialog proccess = new ProgressDialog(context);

        RestClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                proccess.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                proccess.setMessage(context.getResources().getString(R.string.downloading));
                proccess.setCancelable(false);
                proccess.show();            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                proccess.dismiss();
                Toast.makeText(context,context.getFilesDir()+"/prueba.zip",Toast.LENGTH_LONG).show();

                writeBytesToFileClassic(responseBody,context.getFilesDir()+"/prueba.zip");
                ZipManager zipManager = new ZipManager();
                zipManager.unzip(context.getFilesDir()+"/prueba.zip",context.getFilesDir()+"/prueba.mp4");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                proccess.dismiss();
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();

            }
        });

    }

    private static void writeBytesToFileClassic(byte[] bFile, String fileDest) {

        FileOutputStream fileOuputStream = null;

        try {
            fileOuputStream = new FileOutputStream(fileDest);
            fileOuputStream.write(bFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
