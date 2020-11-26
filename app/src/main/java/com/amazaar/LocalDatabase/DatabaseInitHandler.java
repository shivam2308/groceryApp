package com.amazaar.LocalDatabase;

import android.content.Context;
import android.database.Cursor;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

/**
 * Created by Ahmed Fahmy on 3/2/16.
 * Database Init Handler will initialize the Doa database and set the entities
 */
public class DatabaseInitHandler {

    @Inject
    public DaoMaster daoMaster;
    private DaoSession daoSession;
    private LoginEntityDao loginEntityDao;
    private static final String LOGIN_DATABASE_NAME = "amazaar-db";
    private Cursor cursor;
    private Database db;


    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public LoginEntityDao getLoginEntityDao() {
        return loginEntityDao;
    }

    public void setLoginEntityDao(LoginEntityDao loginEntityDao) {
        this.loginEntityDao = loginEntityDao;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    /**
     * Initialize the database.
     * @param context
     */
    public void initDataBase(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, LOGIN_DATABASE_NAME, null);
        db = helper.getWritableDb();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        loginEntityDao = daoSession.getLoginEntityDao();
    }
}