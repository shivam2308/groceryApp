package com.amazaar.Module;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Session.FastSave;
import com.google.inject.Stage;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class AmazaarApplication extends Application {

    private static AmazaarApplication mInstance;
    @Inject
    public LoginEntityDaoHelper m_loginDaoHelper;
    private static Activity m_currentActivity = null;

    public static AmazaarApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }

    public static Activity getCurrentActivity(){
        return m_currentActivity;
    }
    public static void setCurrentActivity(Activity mCurrentActivity){
        m_currentActivity = mCurrentActivity;
    }

    public static FragmentManager getFragmentManager() {
        return m_currentActivity.getFragmentManager();
    }

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();
        FastSave.init(getContext());
        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.setupBaseApplicationInjector(this, Stage.DEVELOPMENT);
    }

    public LoginEntityDaoHelper getLoginEntityDeo() {
        return m_loginDaoHelper;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mInstance != null) {
            mInstance = null;
        }
    }
}

