class Parent {

    public static void staticFn() {
        System.out.println("Parent static fn");
    }

    public void fn() {
        System.out.println("Parent fn");
    }
}

class Child extends Parent {

    // @Override
    public static void staticFn() {
        System.out.println("Child static fn");
    }

    public void fn() {
        System.out.println("Child fn");
    }
}

public class StaticOverriding {
    public static void main(String[] args) {
        Child ob1 = new Child();
        Parent ob2 = new Child();

        System.out.println("Calling static functions:");
        ob1.staticFn();
        ob2.staticFn(); // this shall print "Parent static fn" as static fn can't be overridden
        // in the above example child's static method was hidden. Hence this is called method hiding!

        System.out.println("Calling normal functions:");
        ob1.fn();
        ob2.fn();

        System.out.println("Calling static functions directly (without objetcs):");
        Child.staticFn();
        Parent.staticFn();
    }
}