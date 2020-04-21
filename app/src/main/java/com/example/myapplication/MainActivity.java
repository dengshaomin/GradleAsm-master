package com.example.myapplication;

import java.util.HashMap;

import android.app.Notification.Action;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.example.basecomponent.ComponentRouter;
import com.example.basecomponent.ComponentRouter.ComponentA;
import com.example.march.March;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnTouchListener {

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(this.getClass().getName(), "middle_0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        March.obtains().setContext(this).setName(ComponentA.name).setAction(ComponentRouter.ComponentA.Action.play).setParams(new HashMap<String,
                Object>() {{
            put("key1", "value1");
        }}).excute();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
