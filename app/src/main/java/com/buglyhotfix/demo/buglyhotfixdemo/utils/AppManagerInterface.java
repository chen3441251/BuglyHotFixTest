package com.buglyhotfix.demo.buglyhotfixdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * @ Creator     :     chenchao
 * @ CreateDate  :     2018/2/23 0023 10:35
 * @ Description :     DDCash
 */

public interface AppManagerInterface {
    /**
     * Add to stack
     * 添加到栈
     * @param activity
     */
    void addActivity(Activity activity);

    /**
     * Get the current Activity (the last press in the stack)
     * (获取当前Activity（堆栈中最后一个压入的）)
     * @return
     */
    Activity currentActivity();

    /**
     * End the current Activity (the last press in the stack)
     * 结束当前Activity(堆栈中最后一个压入的)
     */
    void finishCurrentActivity();

    /**
     * End the specified Activity
     * 结束指定的Activity
     * @param activity
     */
    void finishActivity(Activity activity);


    /**
     * Exit to the main Activity
     * 退出到主界面
     */
    void returnHomeActivity();

    /**
     * Close all activity
     * 关闭应用所有的activity
     */
    void finishAllActivity();

    /**
     * Exit the application
     * 退出应用程序
     */
    void appExit();

    /**
     * Double-click to exit the app
     * 双击退出app
     * @param activity
     * @return
     */
    boolean doubleClickExit(Activity activity);

    /**
     * get  homeactivity activity
     * 获取home界面Activty
     * @return
     */
    Activity findHomeActivity();

    /**
     * Get the number of activity in the stack
     * 获取栈里activity的个数
     * @return
     */
    int getStackSize();
    /**
     * 跳转指定activity带有数据
     * @return
     */
    void startActivity(Context context, Class<?> clazz, Bundle bundle);
    /**
     * 跳转指定activity不带数据
     * @return
     */
    void startActivity(Context context, Class<?> clazz);
    /**
     * 跳转指定activity带有数据
     * @return
     */
    void startActivityForResult(Context context, Class<?> clazz, int requestCode, Bundle bundle);
    /**
     * 跳转指定activity不带数据
     * @return
     */
    void startActivityForResult(Context context, Class<?> clazz, int requestCode);
    /**
     * 重启app
     * */
    void resetApp(Context context);
}
