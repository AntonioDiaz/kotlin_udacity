package com;

class Base {
    private static void display() {
        System.out.println("Static o class method from Base");
    }
    public void print() {
        System.out.println("Non-static of instance method from Base");
    }

}


class Derived extends Base {
    private static void display() {
        System.out.println("Static o class method from Derived");
    }
    public void print() {
        System.out.println("Non-static of instance method from Derived");
    }
}

public class Test {
    public static void main(String args[]) {
        Base base = new Derived();
        base.print();
    }
}



