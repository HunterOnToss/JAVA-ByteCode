package hunter.rae.ByteCode;

/**
 * Created by hunter on 18.06.16.
 */
public class Invoke {

    class Class1 {
        public void method1(){};
        public void method2(){};
    }

    interface Interface {
        void method3();
    }

    class Class3 extends Class1 implements Interface {
        public void method3(){};
    }

    class Class4 implements Interface {
        public void method3(){};
    }
}
