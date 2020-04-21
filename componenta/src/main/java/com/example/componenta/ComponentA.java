package com.example.componenta;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.basecomponent.ComponentRouter;
import com.example.basecomponent.ComponentRouter.ComponentA.Action;
import com.example.march.IMarch;

public class ComponentA implements IMarch {

    @Override
    public String getName() {
        return ComponentRouter.ComponentA.name;
    }


    @Override
    public void call(Context context, String name, String action, Map<String, Object> params) {
        Log.e(getName(), "name:" + name + "    " + "action:" + action + "  " + "params:" + params);
        if (action.equals(Action.play) && context != null) {
            Intent intent = new Intent(context, ComponentAActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }
    }
}
