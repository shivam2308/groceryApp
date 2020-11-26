package com.amazaar.LocalDatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.amazaar.LocalDatabase.Enity.LoginEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import javax.inject.Inject;

public class LoginEntityDao extends AbstractDao<LoginEntity, Long> {

    public static final String TABLENAME = "LOGIN_ENTITY";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Eid = new Property(1, String.class, "eid", false, "EID");
        public final static Property Raw_data = new Property(2, String.class, "raw_data", false, "RAW_DATA");
    }

    ;


    @Inject
    public LoginEntityDao(DaoConfig config) {
        super(config);
    }

    public LoginEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_ENTITY\" (" + //
                "\"ID\" INTEGER PRIMARY KEY ," +
                "\"EID\" TEXT," +
                "\"RAW_DATA\" TEXT);");
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_ENTITY\"";
        db.execSQL(sql);
    }


    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public LoginEntity readEntity(Cursor cursor, int offset) {
        LoginEntity entity = new LoginEntity( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // street
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, LoginEntity entity, int offset) {
        entity.setmId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setData(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, LoginEntity entity) {
        stmt.clearBindings();

        Long id = entity.getmId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String eid = entity.getEid();
        if (eid != null) {
            stmt.bindString(2, eid);
        }

        String raw_data = entity.getData();
        if (raw_data != null) {
            stmt.bindString(3, raw_data);
        }
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, LoginEntity entity) {
        stmt.clearBindings();

        Long id = entity.getmId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String eid = entity.getEid();
        if (eid != null) {
            stmt.bindString(2, eid);
        }

        String raw_data = entity.getData();
        if (raw_data != null) {
            stmt.bindString(3, raw_data);
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(LoginEntity entity, long rowId) {
        entity.setmId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(LoginEntity entity) {
        if (entity != null) {
            return entity.getmId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(LoginEntity entity) {
        return false;
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

}