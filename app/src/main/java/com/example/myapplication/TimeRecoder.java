package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class TimeRecoder {

    private static Map<Class, Long> records = new HashMap<>();

    public static synchronized void onStart(Class cls) {
        if (records.containsKey(cls)) {
            return;
        }
        records.put(cls, System.currentTimeMillis());
    }

    public static synchronized void onDestory(Class cls) {
        if (!records.containsKey(cls)) {
            return;
        }
        Log.e("TimeRecoder", cls.getSimpleName() +" keep:" + (System.currentTimeMillis() - records.get(cls)));
        records.remove(cls);
    }
}
