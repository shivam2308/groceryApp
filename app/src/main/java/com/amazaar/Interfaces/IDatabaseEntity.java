package com.amazaar.Interfaces;

import com.amazaar.LocalDatabase.DaoSession;
import com.google.protobuf.GeneratedMessageV3;

import org.greenrobot.greendao.AbstractDao;

import java.io.IOException;

public interface IDatabaseEntity<Dao extends AbstractDao,E extends IData,P extends GeneratedMessageV3> {

    public Dao getDeoEntity();
    public DaoSession getDeoSession();
    public E toEntity(E entity,P message) throws IOException;
    public P toPB(E entity) throws IOException;

}
