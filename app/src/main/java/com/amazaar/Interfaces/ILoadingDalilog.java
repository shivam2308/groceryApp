package com.amazaar.Interfaces;

import android.app.Dialog;

public interface ILoadingDalilog {
    public void setPreviousDailog(Dialog dailog);
    public void setCurrentDailog(Dialog dailog);
    public Dialog getPreviousDailog();
    public Dialog getCurrentDailog();
}
