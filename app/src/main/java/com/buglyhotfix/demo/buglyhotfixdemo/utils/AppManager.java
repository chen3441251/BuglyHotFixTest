package com.buglyhotfix.demo.buglyhotfixdemo.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.buglyhotfix.demo.buglyhotfixdemo.MyTinkerApplicationLike;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ Creator     :     chenchao
 * @ CreateDate  :     2018/2/23 0023 10:35
 * @ Description :     bugly
 */

public class AppManager implements AppManagerInterface {

    private static AppManager      appManager;
    private        Stack<Activity> mActivitiesStack;
    private static String MAINATVITY_NAME="MainActivity";
    //双击退出标志位
    private        boolean         isExit;

    public AppManager() {
    }

    /**
     * 单例
     */
    public static synchronized AppManager get() {
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }

    /**
     * 添加activity
     */
    @Override
    public void addActivity(Activity activity) {
        if (mActivitiesStack == null) {
            mActivitiesStack = new Stack<>();
        }
        mActivitiesStack.add(activity);
    }

    /**
     * 获取最上层activity
     */
    @Override
    public Activity currentActivity() {
        Activity activity = null;
        if (mActivitiesStack != null) {
            activity = mActivitiesStack.lastElement();
        }
        return activity;
    }

    /**
     * 销毁最上层activity
     */
    @Override
    public void finishCurrentActivity() {
        Activity activity = mActivitiesStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 销毁指定activity
     */
    @Override
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (mActivitiesStack != null) {
                mActivitiesStack.remove(activity);
                activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 销毁homeActivy以外的所有activity
     */
    @Override
    public void returnHomeActivity() {
        Activity act = null;
        if (mActivitiesStack != null) {
            for (int i = 0; i < mActivitiesStack.size(); i++) {
                if (null != mActivitiesStack.get(i) && !TextUtils.equals(mActivitiesStack.get(i).getClass().getSimpleName(), MAINATVITY_NAME)) {
                    finishActivity(mActivitiesStack.get(i));
                } else {
                    act = mActivitiesStack.get(i);
                }

            }
            mActivitiesStack.clear();
            if (act != null) {
                mActivitiesStack.add(act);
            }
        }
    }

    /**
     * 销毁所有activity
     */
    @Override
    public void finishAllActivity() {
        if (mActivitiesStack != null) {
            for (Activity activity : mActivitiesStack) {
                finishActivity(activity);
            }
            mActivitiesStack.clear();
        }
    }

    /**
     * 退出app
     */
    @Override
    public void appExit() {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) MyTinkerApplicationLike.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(MyTinkerApplicationLike.getApplicationContext().getPackageName());
        } catch (Exception e) {

        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 双击两次退出app
     */
    @Override
    public boolean doubleClickExit(Activity activity) {
        if (!isExit) {
            isExit = true;
            Toast.makeText(activity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
            return false;
        } else {
            appExit();
            return true;
        }
    }

    /**
     * 获取homeActivity
     */
    @Override
    public Activity findHomeActivity() {
        Activity act = null;
        if (mActivitiesStack != null) {
            for (Activity activity : mActivitiesStack) {
                if (TextUtils.equals(activity.getClass().getSimpleName(), MAINATVITY_NAME)) {
                    act = activity;
                }
            }
        }
        return act;
    }

    /**
     * 获取管理栈大小
     */
    @Override
    public int getStackSize() {
        return mActivitiesStack.size();
    }

    /**
     * 跳转指定activity(携带数据)
     */
    @Override
    public void startActivity(Context context, Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转指定activity(不携带数据)
     */
    @Override
    public void startActivity(Context context, Class<?> clazz) {
        startActivity(context, clazz, null);
    }

    /**
     * 跳转指定activityForResult(携带数据)
     */
    @Override
    public void startActivityForResult(Context context, Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 跳转指定activityForResult(不携带数据)
     */
    @Override
    public void startActivityForResult(Context context, Class<?> clazz, int requestCode) {
        startActivityForResult(context, clazz, requestCode, null);
    }

    @Override
    public void resetApp(Context context) {
        final Intent intent =MyTinkerApplicationLike.getApplicationContext(). getPackageManager().getLaunchIntentForPackage(MyTinkerApplicationLike.getApplicationContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.  startActivity(intent);
    }
}
