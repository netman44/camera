package com.netman44.suferview;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 画在相同Bitmap ，不会闪烁
 *
 * @author: zhiwei
 * @date: 2018-05-19 17:48
 * @version: 9.1.0
 */
public class MyGameSurfaceView2 extends SurfaceView implements SurfaceHolder.Callback, ISurfaceView {
    SurfaceHolder surfaceHolder;
    MyGameThread myGameThread = null;
    int myCanvas_w, myCanvas_h;
    Bitmap myCanvasBitmap = null;
    Canvas myCanvas = null;
    Matrix identityMatrix;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Random random;

    public MyGameSurfaceView2(Context context) {
        super(context);
    }

    public MyGameSurfaceView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGameSurfaceView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myCanvas_w = getWidth();
        myCanvas_h = getHeight();
        myCanvasBitmap = Bitmap.createBitmap(myCanvas_w, myCanvas_h, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas();
        myCanvas.setBitmap(myCanvasBitmap);
        identityMatrix = new Matrix();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    public void MyGameSurfaceView_OnResume() {
        random = new Random();
        surfaceHolder = getHolder();
        getHolder().addCallback(this);
        //Create and start background Thread
        myGameThread = new MyGameThread(this, 200);
        myGameThread.setRunning(true);
        myGameThread.start();
    }

    public void MyGameSurfaceView_OnPause() {
        //Kill the background Thread
        boolean retry = true;
        myGameThread.setRunning(false);
        while (retry) {
            try {
                myGameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //int w = myCanvas.getWidth();
        //int h = myCanvas.getHeight();
        int x = random.nextInt(myCanvas_w - 1);
        int y = random.nextInt(myCanvas_h - 1);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
        myCanvas.drawPoint(x, y, paint);
        canvas.drawBitmap(myCanvasBitmap, identityMatrix, null);
    }

    public void updateStates() {
        //Dummy method() to handle the States
    }

    public void updateSurfaceView() {
        //The function run in background thread, not ui thread.
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                updateStates();
                onDraw(canvas);
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}