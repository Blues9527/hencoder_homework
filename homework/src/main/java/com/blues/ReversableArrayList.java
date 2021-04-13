package com.blues;

import java.util.ArrayList;
import java.util.Collections;

public class ReversableArrayList<T> extends ArrayList<T> {

    public void reverse() {
        Collections.reverse(this);
    }
}
