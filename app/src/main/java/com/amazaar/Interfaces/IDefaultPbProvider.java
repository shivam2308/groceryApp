package com.amazaar.Interfaces;

public interface IDefaultPbProvider<Pb> {
    public Pb getDefaultPb();
    public Pb getPbFromJson(String json);
}
