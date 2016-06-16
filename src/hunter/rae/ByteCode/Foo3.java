package hunter.rae.ByteCode;

/**
 * Created by hunter on 16.06.16.
 */
public class Foo3 {

    public static int noOp() {
        return 2;
    }

    public static int returnConst(int a, int b) {
        return a;
    }

    public void test() {
        double i1 = 0.34;
        double i2 = 0.23;
        int i3 = 0;
        int i4 = 1;
        i1++;
        i2 = i2 + i1;
        i3++;
        i4 = i3 + i4;
    }
}
