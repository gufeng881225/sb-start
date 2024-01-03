package com.sb.common.generic;


import com.sb.common.annotation.MyAnnotation;

public class GenericsClassDemo<U> {

    private U key;

    public GenericsClassDemo(U u) {
        this.key = u;
    }

    public U getKey() {
        return key;
    }

    public <T> T genericMethod(T t) {
        if (t instanceof String) {
            return (T) "sss";
        }
        return t;
    }

    public <T> T show(T t, U u) {
        return null;
    }

    public <T> T show1(T t, T t1) {
        System.out.println(t.getClass());
        System.out.println(t1.getClass());
        return null;
    }

    public <T, K> T show2(T t, K k) {
        System.out.println(t.getClass());
        System.out.println(k.getClass());
        return null;
    }

    @MyAnnotation(name = "PPPP")
    public String show3(String str1, String str2) {
        return str1 + str2;
    }

    public String show4(String str) {
        return str;
    }
}
