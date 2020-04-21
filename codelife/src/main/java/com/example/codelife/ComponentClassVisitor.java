package com.example.codelife;

import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gavin
 * @date 2019/2/18
 * lifecycle class visitor
 */
public class ComponentClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;
    private Map<String,Object> components;
    public ComponentClassVisitor(ClassVisitor cv,Map<String,Object> map) {
        super(Opcodes.ASM5, cv);
        this.components = map;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if("com/example/march/March".equals(this.mClassName) && "<clinit>".equals(name)){
            return new ComponentMethodVisitor(mv,components);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
