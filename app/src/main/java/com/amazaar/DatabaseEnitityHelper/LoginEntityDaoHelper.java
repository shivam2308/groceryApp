package com.amazaar.DatabaseEnitityHelper;

import android.content.Context;

import com.amazaar.Interfaces.IDatabaseEntity;
import com.amazaar.LocalDatabase.AEnityToPbConvertor;
import com.amazaar.LocalDatabase.DaoSession;
import com.amazaar.LocalDatabase.DatabaseInitHandler;
import com.amazaar.LocalDatabase.Enity.LoginEntity;
import com.amazaar.LocalDatabase.LoginEntityDao;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.LoginPbOuterClass;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import java.io.IOException;

import javax.inject.Inject;


public class LoginEntityDaoHelper extends AEnityToPbConvertor<LoginEntity, LoginPbOuterClass.LoginPb> implements IDatabaseEntity<LoginEntityDao,LoginEntity, LoginPbOuterClass.LoginPb> {

    private DatabaseInitHandler m_databaseInitHandler;
    private DaoSession m_daoSession;
    private Context m_context;
    private RequestQueue m_RequestQueue;
    private LoginEntityDao m_loginEntityDao;

    @Inject
    public LoginEntityDaoHelper() {
        m_context = AmazaarApplication.getContext();
        init();
    }

    private void init() {
        m_RequestQueue = Volley.newRequestQueue(m_context);
        m_databaseInitHandler = new DatabaseInitHandler();
        m_databaseInitHandler.initDataBase(m_context);
        m_daoSession = m_databaseInitHandler.getDaoSession();
        m_loginEntityDao = m_daoSession.getLoginEntityDao();
    }

    @Override
    public LoginEntityDao getDeoEntity() {
        return m_loginEntityDao;
    }

    @Override
    public DaoSession getDeoSession() {
        return m_daoSession;
    }

    @Override
    public LoginEntity toEntity(LoginEntity data,LoginPbOuterClass.LoginPb message) throws IOException {
        return fromPb(data,message);
    }

    @Override
    public LoginPbOuterClass.LoginPb toPB(LoginEntity entity) throws IOException {
        if(entity!=null) {
            return fromEntity(entity, LoginPbOuterClass.LoginPb.class);
        }else{
            return LoginPbOuterClass.LoginPb.getDefaultInstance();
        }
    }
    

    public LoginPbOuterClass.LoginPb getLoginPbFromInternalStorage(long id) throws IOException {
        return toPB(getDeoEntity().load(id));
    }
}
