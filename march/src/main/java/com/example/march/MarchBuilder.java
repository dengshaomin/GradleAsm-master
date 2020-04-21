package com.example.march;

import java.util.Map;

import android.content.Context;

public class MarchBuilder {
    private String action;
    private String name;
    private Map<String,Object> params;
    private Context mContext;
    private March mMarch;
    public MarchBuilder(March march){
        mMarch = march;
    }
    public MarchBuilder setContext(Context context) {
        mContext = context;
        return this;
    }

    public MarchBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public MarchBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MarchBuilder setParams(Map<String, Object> params) {
        this.params = params;
        return  this;
    }

    public void excute(){
        mMarch.excute(mContext,name,action,params);
    }
}
