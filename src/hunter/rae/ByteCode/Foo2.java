package hunter.rae.ByteCode;

/**
 * Created by hunter on 16.06.16.
 */
public class Foo2 {

    private String m = new String("Hi!");


    public static int negative(int i) {

        switch (i) {
            case 1: return -1;
            case 2: return -2;
            case 3: return -3;
            case 4: return -4;
            default: return 0;
        }

    }

    public static int negative2(int i) {

        switch (i) {
            case 1: return -1;
            case 2: return -2;
            case 3: return -3;
            case 4: return -4;
            case 100: return -100;
            default: return 0;
        }
    }

    public static String nagetiveString (String i) {

        switch (i) {
            case "a": return "-a";
            case "b": return "-b";

            default: return "";
        }

    }

    public static String st (String i) {

        switch (i) {
            case "unknown": return "i";
            case "new": return "i";
            case "admin": return "i";
            case "user": return "i";
            case "superuser": return "i";
            default: return "b";
        }
    }
}
