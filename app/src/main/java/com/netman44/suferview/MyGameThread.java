package com.netman44.suferview;

/**
 * 在此写用途
 *
 * @author: zhiwei
 * @date: 2018-05-19 17:27
 * @version: 9.1.0
 */
public class MyGameThread extends Thread{
    ISurfaceView mSurfaceView;
    long mTime;
    boolean mRunning = false;


    public MyGameThread(ISurfaceView surfaceView, long time) {
        mSurfaceView = surfaceView;
        mTime = time;
    }

    public MyGameThread setRunning(boolean running) {
        mRunning = running;
        return this;
    }

    @Override
    public void run() {
        super.run();
        while (mRunning) {
            try {
                synchronized (this) {
                    this.wait(mTime);
                }

                mSurfaceView.updateSurfaceView();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
