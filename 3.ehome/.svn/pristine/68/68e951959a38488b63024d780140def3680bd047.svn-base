package com.its.test.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class testComparator {
    public static void main(String[] args) {
        String str1 = "03,09,30,20,";
        String str2 = "4,7,30,40,";
        System.out.println( strComparator(str1, str2));
      String[] str=strComparator(str1, str2).split("\\|");
      System.out.println( str[0]);
      System.out.println( str[1]);
    }

    public static String  strComparator(String str1, String str2) {
        Map<String, String> map = new HashMap<>();
        String[] arrStr1 = str1.split(",");
        String[] arrStr2 = str2.split(",");
        for (int i = 0; i < arrStr1.length; i++) {
            map.put(arrStr2[i],arrStr1[i]);
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arrStr1.length; i++) {
            list.add(Integer.parseInt((arrStr2[i])));
        }
        Collections.sort(list);
        System.out.println(list);
        StringBuilder strb1=new StringBuilder();
        StringBuilder strb2=new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            strb1.append(map.get(list.get(i)+"")).append(",");
            strb2.append(list.get(i)).append(",");
        }
        return strb1+"|"+strb2;
    }

}