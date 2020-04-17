package com.example.codelife;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * @author gavin
 * @date 2019/2/19
 */
public class TimeRecordEndVisitor extends MethodVisitor {

    public TimeRecordEndVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        }



    @Override
    public void visitInsn(int opcode) {
        if(opcode == Opcodes.RETURN){
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/example/myapplication/TimeRecoder", "onDestory", "(Ljava/lang/Class;)V", false);
        }
        super.visitInsn(opcode);

    }

}
