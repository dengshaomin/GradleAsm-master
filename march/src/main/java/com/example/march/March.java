package com.example.march;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

public class March {

    private static volatile Map<String, IMarch> components = new HashMap<>();


    private static March march;

    static {
    }

    private static void addComponent(String name) {
        if (TextUtils.isEmpty(name) || components.containsKey(name)) {
            return;
        }
        name = name.replace(File.separator, ".");
        try {
            name = name.substring(0, name.length() - 6);
            Class cls = Class.forName(name);
            if (cls != null) {
                IMarch iMarch = (IMarch) cls.newInstance();
                String componentName = iMarch.getName();
                if (TextUtils.isEmpty(componentName)) {
                    return;
                }
                components.put(componentName, iMarch);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    public static MarchBuilder obtains() {
        if (march == null) {
            synchronized (March.class) {
                if (march == null) {
                    march = new March();
                }
            }
        }
        return new MarchBuilder(march);
    }

    public void excute(Context context, String name, String action, Map<String, Object> params) {
        if (components == null || !components.containsKey(name)) {
            return;
        }
        IMarch march = components.get(name);
        if (march != null) {
            march.call(context, name, action, params);
        }
    }
}
