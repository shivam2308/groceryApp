package com.amazaar.Module;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.DatabaseEnitityHelper.ItemEntityDaoHelper;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Session.FastSave;
import com.google.inject.Stage;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class AmazaarApplication extends Application {

    private static AmazaarApplication mInstance;
    private static Activity m_currentActivity = null;

    private static String m_deviceToken;
    @Inject
    public LoginEntityDaoHelper m_loginDaoHelper;
    @Inject
    public ItemEntityDaoHelper m_itemDaoHelper;
    @Inject
    public CartEntityDaoHelper m_cartDaoHelper;

    public static AmazaarApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }

    public static Activity getCurrentActivity() {
        return m_currentActivity;
    }

    public static void setCurrentActivity(Activity mCurrentActivity) {
        m_currentActivity = mCurrentActivity;
    }

    public static String getDeviceToken() {
        return m_deviceToken;
    }

    public static void setDeviceToken(String token) {
        m_deviceToken = token;
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

    public ItemEntityDaoHelper getItemEntityDeo() {
        return m_itemDaoHelper;
    }

    public CartEntityDaoHelper getCartEntityDeo() {
        return m_cartDaoHelper;
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

