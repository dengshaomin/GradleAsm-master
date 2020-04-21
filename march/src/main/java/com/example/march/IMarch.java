package com.example.march;

import java.util.Map;

import android.content.Context;

public interface IMarch {

    String getName();


    void call(Context context, String name, String action, Map<String, Object> params);
}
