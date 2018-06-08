package com.google.android.cameraview;

import java.util.Set;

/**
 * 在此写用途
 *
 * @author: zhiwei
 * @date: 2018-06-08 16:56
 * @version: 9.1.0
 */
public interface CameraViewInterface {
    /**
     * @return {@code true} if the implementation was able to start the camera session.
     */
     boolean start();

     void stop();

     boolean isCameraOpened();

     void setFacing(int facing);

     int getFacing();

     Set<AspectRatio> getSupportedAspectRatios();

    /**
     * @return {@code true} if the aspect ratio was changed.
     */
     boolean setAspectRatio(AspectRatio ratio);

     AspectRatio getAspectRatio();

     void setAutoFocus(boolean autoFocus);

     boolean getAutoFocus();

     void setFlash(int flash);

     int getFlash();

     void takePicture();

     void setDisplayOrientation(int displayOrientation);

}
