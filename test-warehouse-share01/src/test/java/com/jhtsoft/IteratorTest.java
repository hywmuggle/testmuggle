package com.jhtsoft;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName: IteratorTest
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/10
 **/
public class IteratorTest {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("c");
        list.add("a");
        list.add("b");
        list.add("d");
        list.add("e");
        list.add("g");
        list.add("f");
        Iterator it = list.iterator();
        while(it.hasNext()){
            String str = (String) it.next();
            System.out.println(str);
        }
    }
}
