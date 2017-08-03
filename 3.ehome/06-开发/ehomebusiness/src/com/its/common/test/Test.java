package com.its.common.test;

import com.its.common.utils.DateUtils;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        System.out.printf( DateUtils.getWeek(DateUtils.getDateAfter(new Date(),5)));
    }
}
