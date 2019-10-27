package com.example.myapplication;


import android.app.IntentService;
import android.content.Context;
import android.drm.DrmStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TouchViewGroup extends LinearLayout {
    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("code111", this.getClass().getName() + "  dispatchTouchEvent:" + event.getAction() + "");
        return super.dispatchTouchEvent(event);
    }

    int count = 0;
    private boolean hasInterceptMoveEvent;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if( ev.getAction() == MotionEvent.ACTION_MOVE){
//            count++;
//            return true;
//        }
        if (!hasInterceptMoveEvent && ev.getAction() == MotionEvent.ACTION_MOVE) {
            hasInterceptMoveEvent = true;
            return true;
        }
        Log.e("code111", this.getClass().getName() + "  onInterceptTouchEvent:" + ev.getAction() + "");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("code111", this.getClass().getName() + " onTouchEvent:" +event.getAction() + "");
        return true;
//        }
//        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
