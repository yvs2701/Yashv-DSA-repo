class SuperClass {
    public void method1() {
        System.out.println("superclass method1");
        this.method2();
    }

    public void method2() {
        System.out.println("superclass method2");
    }
}

class SubClass extends SuperClass {
    public void method1() {
        System.out.println("subclass method1");
        super.method1();
    }

    public void method2() {
        System.out.println("subclass method2");
    }
}

public class SuperKeywordConfusion {
    public static void main(String[] args) {
        SubClass ob = new SubClass();
        ob.method1();

        /* EXPECTED OUTPUT:
         * subclass method1
         * superclass method1
         * superclass method2 */

        /* ACTUAL OUTPUT:
         * subclass method1
         * superclass method1
         * subclass method2 */

        /* The form "super.Identifier" refers to the field named "Identifier" of the current object,
         * but with the current object viewed as an instance of the superclass of the current class.
         * src: https://stackoverflow.com/questions/4595512/java-calling-a-super-method-which-calls-an-overridden-method */
    }
}