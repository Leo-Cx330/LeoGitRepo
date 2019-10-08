package com.leo.app.api;

public class Test {

    public static void main(String[] args) {
        System.out.println(new Test().get());
    }

    public  int get(){
        try {
            return 1;
        }finally {
            return 2;
        }
    }
    class  A {
        public  A(){

        }
    }
}
