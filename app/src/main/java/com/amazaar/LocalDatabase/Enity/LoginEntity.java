package com.amazaar.LocalDatabase.Enity;

import com.amazaar.Interfaces.IData;

import javax.inject.Inject;

public class LoginEntity implements IData {
    private Long id;
    private String eid;
    private String raw_data;


    @Inject
    public LoginEntity() {
    }

    public LoginEntity(Long eid) {
        this.id = id;
    }



    public LoginEntity(Long id, String eid, String raw_data) {
        this.id = id;
        this.eid = eid;
        this.raw_data = raw_data;
    }
    public Long getmId() {
        return id;
    }

    public void setmId(Long id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getData() {
        return raw_data;
    }

    public void setData(String raw_data) {
        this.raw_data = raw_data;
    }
}