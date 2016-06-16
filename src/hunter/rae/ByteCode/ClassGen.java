package hunter.rae.ByteCode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;

/**
 * Created by hunter on 17.06.16.
 */
public class ClassGen {

    public static void main(String[] args) {

    }

    private byte[] generateSummatorClass() {

        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        cw.visit(51, Opcodes.ACC_PUBLIC, "Summator", null, "java/lang/Object", null);
        generateDefaultConstructor(cw);
        generateSummMethod(cw);
        cw.visitEnd();

        return cw.toByteArray();
    }

    private void generateDefaultConstructor(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private void generateSummMethod(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC,
                "sum", // method name
                "(II)I", // method descriptor
                null, // exceptions
                null); // method attributes

        mv.visitCode();

//        TASK:

//        public class Summator{
//
//            public int sum(int a, int b) {
//                return a + b;
//            }
//
//        }

        mv.visitEnd();
    }
}
