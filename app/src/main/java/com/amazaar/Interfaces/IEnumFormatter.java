package com.amazaar.Interfaces;

public interface IEnumFormatter<E> {
    public String format(E data);

    public E getEnum(String data);
}
