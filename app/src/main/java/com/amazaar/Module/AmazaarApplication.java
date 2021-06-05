package com.amazaar.Module;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.multidex.MultiDex;

import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.DatabaseEnitityHelper.ItemEntityDaoHelper;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.ImageCache.ItemImageCache;
import com.amazaar.R;
import com.amazaar.Session.FastSave;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.inject.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class AmazaarApplication extends Application {

    private static final Stage mode = Stage.PRODUCTION;
    private static AmazaarApplication mInstance;
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static Activity m_currentActivity = null;
    private static Context m_currentContext = null;

    private static Fragment m_currentFragment;

    private static Dialog m_dialog;

    private static String m_deviceToken;

    public static ItemImageCache m_memoryCache;
    @Inject
    public LoginEntityDaoHelper m_loginDaoHelper;
    @Inject
    public ItemEntityDaoHelper m_itemDaoHelper;
    @Inject
    public CartEntityDaoHelper m_cartDaoHelper;

    public FirebaseInstanceId m_firebaseInstanse;

    public static Stage getMode() {
        return mode;
    }

    public static Fragment getCurrentFragment() {
        return m_currentFragment;
    }

    public static void setCurrentFragment(Fragment fragment) {
        m_currentFragment = fragment;
    }

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

    public static ExecutorService getExecutor() {
        return executorService;
    }

    public static Dialog getLoadingDialog() {
        return m_dialog;
    }

    public static void setDeviceToken(String token) {
        m_deviceToken = token;
    }

    public static FragmentManager getFragmentManager() {
        return m_currentActivity.getFragmentManager();
    }

    public static void setCurrentContext(Context context) {
        m_currentContext = context;
    }

    public static Context getCurrentContext() {
        return m_currentContext;
    }

    public static ItemImageCache getItemImageCache() {
        return m_memoryCache;
    }

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();
        FastSave.init(getContext());
        RoboGuice.setUseAnnotationDatabases(false);
        RoboGuice.setupBaseApplicationInjector(this, mode);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m_deviceToken = FirebaseInstanceId.getInstance().getToken("796220644087", "FCM");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        m_memoryCache = new ItemImageCache();
    }

    public static void createLoadingDailog() {
        Dialog dialog = new Dialog(getCurrentActivity());
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimationTultip;
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_loading);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        m_dialog = dialog;
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

