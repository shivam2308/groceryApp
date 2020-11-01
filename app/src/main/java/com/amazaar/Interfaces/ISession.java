package com.amazaar.Interfaces;

public interface ISession<T> {

    public T getSession();

    public void setSession(T value);

    public void clear();

    public boolean sessionExixts();

    public T updateSession(T value);
}
