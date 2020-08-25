package com.flying.middleware.rabbit.queue;

public class Test {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<String>();
        for(String s : "the prefect code".split(" ")){//LIFO
            myList.push(s);
        }
        while(!myList.empty()){
            System.out.print(myList.peek()+" ");
            myList.remove();
        }
    }
}
