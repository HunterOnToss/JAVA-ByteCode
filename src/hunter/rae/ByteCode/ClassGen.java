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

        cw.visit(51,
                Opcodes.ACC_PUBLIC, // Флаги(атрибуты) класса
                "Summator",         // Имя класса
                null,               //
                "java/lang/Object", // класс от которого наследуется
                null);
        generateDefaultConstructor(cw); // генерируем байт-код конструктора по умолчанию
        generateSummMethod(cw);         // генерируем метод sum
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
}
