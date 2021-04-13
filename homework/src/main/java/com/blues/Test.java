package com.blues;

import java.io.Serializable;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Eater<String> eater = new Eater<String>() {
            @Override
            public void eat(String food) {
                System.out.println("eater eat: " + food);
            }

            @Override
            public <E extends Runnable & Serializable> void someMethod(E param) {

            }
        };
        eater.eat("noodle");
        eater.someMethod(new A());
    }


    static class A implements Runnable, Serializable {

        @Override
        public void run() {

        }
    }


    public <T> void merge(T item, List<T> list) {
        list.add(item);

    }
}
