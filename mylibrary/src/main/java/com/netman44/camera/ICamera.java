package com.netman44.camera;

/**
 * 摄像头操作接口
 *
 * @author: zhiwei
 * @date: 2018-05-19 15:57
 * @version: 9.1.0
 */
public interface ICamera {
    /**
     * 设置回调
     * @param callBack
     */
    void setCameraCallBack(CameraCallBack callBack);

    /**
     * 打开摄像头。
     * @param isFrontFace true 前置摄像头，false 后置摄像头
     * @return 打开摄像头失败
     */
    boolean openCamera(boolean isFrontFace);

    /**
     * 开始预览
     */
    void doStartPreview();

    /**
     * 停止预览
     */
    void doStopPreview();

    /**
     * 是否Camera自愿
     */
    void doStopCamera();

    /**
     * 切换摄像头
     * @param isFront
     * @return
     */
    boolean doSwitchCameraFaceing(boolean isFront);

    boolean doSwitchFlashMode(int flashMode);

    void doSwitchCameraRate();


    /**
     * 拍摄照片
     * @param x
     * @param y
     * @param width
     * @param height
     */
    void doTackPiture(int x ,int y, int width, int height);





    public interface  CameraCallBack{

    }
}
