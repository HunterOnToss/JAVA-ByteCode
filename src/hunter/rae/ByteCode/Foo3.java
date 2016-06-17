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

    public static int mod(final int val) {
        if (val >= 0) {
            return val;
        } else {
            return -val;
        }
    }

    public static int mod2(final int val) {
        final int result;
        if (val >= 0) {
            result = val;
        } else {
            result = -val;
        }

        return result;
    }

    public static int max(final int left, final int right) {
        if (left >= right) {
            return left;
        } else {
            return right;
        }
    }

    public static double max(final double left, final double right) {
        if (left >= right) {
            return left;
        } else {
            return right;
        }
    }

    public static float max(final float left, final float right) {
        if (left >= right) {
            return left;
        } else {
            return right;
        }
    }

}
