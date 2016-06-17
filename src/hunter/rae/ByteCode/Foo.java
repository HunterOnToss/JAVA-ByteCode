package hunter.rae.ByteCode;

/**
 * Created by hunter on 16.06.16.
 */
public class Foo {

    private String m = "Hello world";

    public static int forLoop() {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result = result + i;
        }

        return result;
    }

//    public static forLoop()I
//    L0
//    LINENUMBER 11 L0
//            ICONST_0
//    ISTORE 0
//    L1
//    LINENUMBER 12 L1
//            ICONST_0
//    ISTORE 1
//    L2
//    FRAME APPEND [I I]
//    ILOAD 1
//    BIPUSH 10 -- Кладет 10 на стек что бы занимать 2 байта, а не 4.
//    IF_ICMPGE L3
//    L4
//    LINENUMBER 13 L4
//    ILOAD 0
//    ILOAD 1
//    IADD
//    ISTORE 0
//    L5
//    LINENUMBER 12 L5
//    IINC 1 1
//    GOTO L2
//    L3
//    LINENUMBER 16 L3
//    FRAME CHOP 1
//    ILOAD 0
//    IRETURN
//            L6
//    LOCALVARIABLE i I L2 L3 1
//    LOCALVARIABLE result I L1 L6 0
//    MAXSTACK = 2
//    MAXLOCALS = 2

    public static int forLoop2() {
        int result = 0;
        int i = 0;

        loop: for(;;) if (i < 10) {
            result = result + i;
            i++;
            continue loop;
        } else {
            return result;
        }
    }

}
