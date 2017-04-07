package com.zhiyou100.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lijike 2016/12/6.
 */
public class DateConverter implements Converter<String,Date> {

    private static final List<String> formarts = new ArrayList<String>(4);

    static {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }


    @Override
    public Date convert(String s) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        try {
            String formatter = "";
            if (s.matches("^\\d{4}-\\d{1,2}$")) {
                formatter = formarts.get(0);
            } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                formatter = formarts.get(1);
            } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
                formatter = formarts.get(2);
            } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                formatter = formarts.get(3);
            } else {
                throw new IllegalArgumentException("Invalid boolean value '" + s + "'");
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatter);
            DateTime dateTime = DateTime.parse(s,dateTimeFormatter);
            return dateTime.toDate();
        } catch (Exception e){
            return null;
        }

    }
}
