package hunter.rae.ByteCode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
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

        cw.visit(51,
                Opcodes.ACC_PUBLIC, // Флаги(атрибуты) класса
                "Summator",         // Имя класса
                null,               //
                "java/lang/Object", // класс от которого наследуется
                null);
        generateDefaultConstructor(cw); // генерируем байт-код конструктора по умолчанию
        generateSummMethod(cw);         // генерируем метод sum

        generateMinMethod(cw);
        generateMin2Method(cw);

        cw.visitEnd();                  // заканчиваем генерацию класса

        return cw.toByteArray();        // возвращаем массив, который содержит байт код
    }

    private void generateDefaultConstructor(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC,  // флаги (атрибуты) метода, который мы генерируем
                "<init>", // имя метода;
                "()V", // тип возвращаемых данных; конструктор ничего не возвращает.
                null,
                null);
        mv.visitCode();
        /* метод visitVarInsn используется для добавления инструкции, которая работает с локальными переменными.
        К таким инструкциям относятся: ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE, RET
        Первым аргументом передается инструкция, а вторым номер переменной, над которой нужно выполнить инструкцию.*/
        mv.visitVarInsn(Opcodes.ALOAD, 0);  // добавляем в стек локальную переменную 0 (это указатель на this).
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        /* метод visitInsn используется для генерации инструкций байт-кода, которые не требуют никаких аргументов на вход.
        К ним относятся: IRETURN/RETURN и многие другие.*/
        mv.visitInsn(Opcodes.RETURN); // заканчиваем метод
        mv.visitMaxs(1, 1);  // устанавливаем размер стека в 1 и количество локальных перменных
        mv.visitEnd();
    }

    private void generateSummMethod(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC,
                "sum", // method name
                "(II)I", // method descriptor
                null, // exceptions
                null); // method attributes

        mv.visitCode();

        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitInsn(Opcodes.IADD);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(2, 3);

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

    private void generateMinMethod(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "min", // method name
                "(II)I", // method descriptor
                null, // exceptions
                null // method attributes
        );

        mv.visitCode();
        final Label elseLabel = new Label();

//        public static int min(int a, int b) {
//            if (a <= b)
//                return a;
//            else
//                return b;
//        }

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitJumpInsn(Opcodes.IF_ICMPLT, elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitLabel(elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(2, 2);

        mv.visitEnd();
    }

    private void generateMin2Method(final ClassWriter cw ) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "min",    // method name
                "(III)I", // method descriptor
                null,     // exceptions
                null);    // method attributes
        mv.visitCode();
        final Label elseLabel = new Label();
        final Label elseLabel2 = new Label();

//        public static int min(int a, int b, int c) {
//            if (a < b && a < c) return a;
//            if (b < c) return b;
//            return c;
//        }

        // comparing local variable 0 with 1 and saving min to the 0
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ISTORE, 0);
        mv.visitLabel(elseLabel);

        // comparing local variable 0 with 2 and saving min to the 0
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel2);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ISTORE, 0);
        mv.visitLabel(elseLabel2);

        // returning local variable 0 (since it's the minimum)
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(2, 2);

        mv.visitMaxs(2, 2);

        mv.visitEnd();
    }
}
