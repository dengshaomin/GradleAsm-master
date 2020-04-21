package com.example.codelife;

import java.util.Map;

import org.apache.tools.ant.taskdefs.optional.depend.constantpool.StringCPInfo;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * @author gavin
 * @date 2019/2/19
 */
public class ComponentMethodVisitor extends MethodVisitor {

    private Map<String, Object> maps;

    public ComponentMethodVisitor(MethodVisitor mv, Map<String, Object> map) {
        super(Opcodes.ASM4, mv);
        this.maps = map;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //方法执行前插入
    }

    @Override
    public void visitInsn(int opcode) {
        //方法执行后插入
        if (opcode == Opcodes.RETURN && maps != null && maps.size() > 0) {
            System.out.println("------MethodVisitor" + "    " + maps);
            for (String key : maps.keySet()) {
                mv.visitLdcInsn(key);
                mv.visitMethodInsn(INVOKESTATIC, "com/example/march/March", "addComponent", "(Ljava/lang/String;)V", false);
            }
        }
        super.visitInsn(opcode);
    }
}
