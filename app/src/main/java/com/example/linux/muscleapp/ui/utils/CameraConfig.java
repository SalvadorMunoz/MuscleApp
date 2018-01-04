package com.example.linux.muscleapp.ui.utils;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.util.Log;

import java.util.List;

/**
 * Created by linux on 4/01/18.
 */

public class CameraConfig {
    public static void setResolution(){
        Camera camera = Camera.open();
        Camera.Parameters params = camera.getParameters();

        // Check what resolutions are supported by your camera
        List<Camera.Size> sizes = params.getSupportedPictureSizes();

        // Iterate through all available resolutions and choose one.
        // The chosen resolution will be stored in mSize.
        Camera.Size mSize = null;
        for (Camera.Size size : sizes) {
            if (wantToUseThisResolution(size)) {
                    mSize = size;
                    break;
                }
            }

            params.setPictureSize(mSize.width, mSize.height);
            camera.setParameters(params);

        }

    private static boolean wantToUseThisResolution(Camera.Size size) {
        if(size.width == 800 && size.height ==600)
            return  true;
        return false;
    }

}
//768x1024
//800x600