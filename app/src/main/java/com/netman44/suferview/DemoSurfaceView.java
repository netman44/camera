package com.netman44.suferview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 在此写用途
 *
 * @author: zhiwei
 * @date: 2018-05-19 16:59
 * @version: 9.1.0
 */
public class DemoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private LoopThread thread;

    public DemoSurfaceView(Context context) {
        super(context);
        init(); //初始化,设置生命周期回调方法
    }


    public DemoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(); //初始化,设置生命周期回调方法
    }

    public DemoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(); //初始化,设置生命周期回调方法

    }

    private void init(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
        thread = new LoopThread(holder, getContext());

        //透明背景
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning = true;
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 执行绘制的绘制线程
     * @author Administrator
     *
     */
    class LoopThread extends Thread{
        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;
        public LoopThread(SurfaceHolder surfaceHolder,Context context){
            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
        }
        @Override
        public void run() {
            Canvas c = null;
            while(isRunning){
                try{
                    synchronized (surfaceHolder) {
                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);
                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
        public void doDraw(Canvas c){
            //这个很重要，清屏操作，清楚掉上次绘制的残留图像
            c.drawColor(Color.TRANSPARENT, Mode.CLEAR);
            c.translate(200, 200);
            c.drawCircle(0,0, radius++, paint);
            if(radius > 100){
                radius = 10f;
            }
        }
    }
}
