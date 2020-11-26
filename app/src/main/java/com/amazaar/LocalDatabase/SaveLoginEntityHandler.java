package com.amazaar.LocalDatabase;


import com.amazaar.LocalDatabase.Enity.LoginEntity;
import com.amazaar.LocalDatabase.model.LoginData;

public final class SaveLoginEntityHandler {

    private SaveLoginEntityHandler() {}

    /**
     * Save property will take the login object and convert it into a BathroomEntity Object
     * and then insert it into the daosession.
     * @param daoSession
     * @param login
     */
    public static void saveProperty(DaoSession daoSession, LoginData login){
        LoginEntity entity = new LoginEntity();
        entity.setmId(login.getmId());
        entity.setEid(login.getEid());
        daoSession.insertOrReplace(entity);
    }
}
