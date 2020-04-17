package com.example.myapplication;

import android.util.Log;

public class AsmClass1 {
    void oncreate(){
        TimeRecoder.onStart(this.getClass());
    }

    void oncreate1(){
        TimeRecoder.onDestory(this.getClass());
    }

}
