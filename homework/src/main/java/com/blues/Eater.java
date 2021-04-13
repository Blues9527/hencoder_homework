package com.blues;

import java.io.Serializable;

public interface Eater<T> {

    void eat(T food);


    <E extends Runnable & Serializable> void someMethod(E param);


}
