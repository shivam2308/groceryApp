package com.amazaar.CommonCode;

import com.amazaar.Interfaces.ISession;
import com.amazaar.Session.FastSave;
import com.google.protobuf.GeneratedMessageV3;

import java.util.List;

import javax.inject.Inject;

public class ASession<T extends GeneratedMessageV3> extends FastSave implements ISession<T> {

    private String m_key = "";
    Class<T> m_classType;
    private SessionListener listener;

    @Inject
    public ASession(String key,Class<T> classType) {
        super();
        m_key=key;
        m_classType=classType;
    }

    public void saveInt(int value) {
        super.saveInt(m_key, value);
        if (listener != null) listener.onSessionChange();
    }

    public int getInt(int defaultValue) {
        return super.getInt(m_key, defaultValue);
    }

    public void saveBoolean(boolean value) {
        super.saveBoolean(m_key, value);
        if (listener != null) listener.onSessionChange();
    }

    public boolean getBoolean(boolean defaultValue) {
        return super.getBoolean(m_key, defaultValue);
    }

    public void saveFloat(float value) {
        super.saveFloat(m_key, value);
        if (listener != null) listener.onSessionChange();
    }


    public float getFloat(float defaultValue) {
        return super.getFloat(m_key, defaultValue);

    }


    public void saveLong(long value) {
        super.saveLong(m_key, value);
        if (listener != null) listener.onSessionChange();
    }

    public long getLong(long defaultValue) {
        return super.getLong(m_key, defaultValue);
    }

    public void saveString(String value) {
        super.saveString(m_key, value);
        if (listener != null) listener.onSessionChange();
    }

    public String getString(String defaultValue) {
        return super.getString(m_key, defaultValue);
    }


    public <T> void saveObject(T object) {
        super.saveObject(m_key, object);
        if (listener != null) listener.onSessionChange();
    }


    public <T> T getObject() {
        return (T) super.getObject(m_key, m_classType);
    }


    public <T> void saveObjectsList(List<T> objectList) {
        super.saveObjectsList(m_key, objectList);
        if (listener != null) listener.onSessionChange();
    }


    public <T> List<T> getObjectsList(Class<T> classType) {
        return super.getObjectsList(m_key, classType);
    }

    @Override
    public void clearSession() {
        super.clearSession();
        if (listener != null) listener.onSessionChange();
    }

    @Override
    public boolean deleteValue(String key) {
        return super.deleteValue(key);
    }

    @Override
    public boolean isKeyExists(String key) {
        return super.isKeyExists(key);
    }

    public SessionListener getListener() {
        return listener;
    }

    public void setListener(SessionListener listener) {
        this.listener = listener;
    }

    @Override
    public T getSession() {
        return getObject();
    }

    public interface SessionListener {
        void onSessionChange();
    }
}
