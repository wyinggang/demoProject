package com.domain;

public  class GenericTest<T> {


    public T m1(T t){

        return t;
    }

    public <T extends Number> T m2(T t){

        return t;
    }

    public static void main(String[] args) {
        GenericTest<String> genericTest = new GenericTest<>();

        System.out.println(genericTest.m1("11"));

        System.out.println(genericTest.m2(1));
    }

}
