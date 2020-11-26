package com.amazaar.Interfaces;

import com.google.protobuf.GeneratedMessageV3;

import java.util.concurrent.ExecutionException;

public interface IClientServices<Pb extends GeneratedMessageV3, Req extends GeneratedMessageV3, Resp extends GeneratedMessageV3> {
    public Pb get(String id) throws ExecutionException, InterruptedException;

    public Pb create(Pb request) throws ExecutionException, InterruptedException;

    public Pb update(Pb request) throws ExecutionException, InterruptedException;

    public Resp search(Req request) throws ExecutionException, InterruptedException;
}
