package com.amazaar.LocalDatabase;

import com.amazaar.CommonCode.ProtoJsonUtil;
import com.amazaar.Interfaces.IData;
import com.google.protobuf.GeneratedMessageV3;

import java.io.IOException;

import javax.inject.Inject;

public class AEnityToPbConvertor<E extends IData, P extends GeneratedMessageV3> {

    @Inject
    public AEnityToPbConvertor() {
    }


    public E fromPb(E entity, P pb) throws IOException {
        entity.setData(ProtoJsonUtil.toJson(pb));
        return entity;
    }

    public P fromEntity(E entity, Class clazz) throws IOException {
        return (P) ProtoJsonUtil.fromJson(entity.getData(), clazz);
    }
}
