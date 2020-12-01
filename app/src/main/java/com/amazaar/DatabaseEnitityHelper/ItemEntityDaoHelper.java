package com.amazaar.DatabaseEnitityHelper;

import android.content.Context;

import com.amazaar.Interfaces.IDatabaseEntity;
import com.amazaar.LocalDatabase.AEnityToPbConvertor;
import com.amazaar.LocalDatabase.DaoSession;
import com.amazaar.LocalDatabase.DatabaseInitHandler;
import com.amazaar.LocalDatabase.Enity.ItemEntity;
import com.amazaar.LocalDatabase.ItemEntityDao;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

import javax.inject.Inject;

public class ItemEntityDaoHelper extends AEnityToPbConvertor<ItemEntity, ItemPbOuterClass.ItemPb> implements IDatabaseEntity<ItemEntityDao, ItemEntity, ItemPbOuterClass.ItemPb> {

    private DatabaseInitHandler m_databaseInitHandler;
    private DaoSession m_daoSession;
    private Context m_context;
    private RequestQueue m_RequestQueue;
    private ItemEntityDao m_itemEntityDao;

    @Inject
    public ItemEntityDaoHelper() {
        m_context = AmazaarApplication.getContext();
        init();
    }

    private void init() {
        m_RequestQueue = Volley.newRequestQueue(m_context);
        m_databaseInitHandler = new DatabaseInitHandler();
        m_databaseInitHandler.initDataBase(m_context);
        m_daoSession = m_databaseInitHandler.getDaoSession();
        m_itemEntityDao = m_daoSession.getItemEntityDao();
    }

    @Override
    public ItemEntityDao getDeoEntity() {
        return m_itemEntityDao;
    }

    @Override
    public DaoSession getDeoSession() {
        return m_daoSession;
    }

    @Override
    public ItemEntity toEntity(ItemEntity data, ItemPbOuterClass.ItemPb message) throws IOException {
        return fromPb(data, message);
    }

    @Override
    public ItemPbOuterClass.ItemPb toPB(ItemEntity entity) throws IOException {
        if (entity != null) {
            return fromEntity(entity, ItemPbOuterClass.ItemPb.class);
        } else {
            return ItemPbOuterClass.ItemPb.getDefaultInstance();
        }
    }


    public ItemPbOuterClass.ItemPb getLoginPbFromInternalStorage(long id) throws IOException {
        return toPB(getDeoEntity().load(id));
    }
}