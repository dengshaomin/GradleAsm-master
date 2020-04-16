package com.example.codelife;

import java.io.IOException;
import java.util.Set;

import com.android.build.api.transform.QualifiedContent.ContentType;
import com.android.build.api.transform.QualifiedContent.Scope;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;


public class MyTransform extends Transform {

    @Override
    public String getName() {
        System.out.println("getName");
        return "MyTransform";
    }

    @Override
    public Set<ContentType> getInputTypes() {
        return null;
    }

    @Override
    public Set<? super Scope> getScopes() {
        return null;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        System.out.println("111111111");
    }
}
