package com.its.common.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数字操作工具类
 * @author liuhl
 *
 */
public class NumberUtil extends NumberUtils{
    /**
     * Double显示两位小数（四舍五入）
     * NULL不显示
     * @param number 待整形数字
     */
    public static String doubleFormat(Double number) {
        if (number == null) {
            return "";
        }
        if (number.equals(DOUBLE_ZERO)) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat(".00");
        df.setRoundingMode(RoundingMode.HALF_UP);  
        return df.format(number);
    }
    
    /**
     * 避免科学计数法的出现
     * NULL不显示
     * @param number 待整形数字
     */
    public static String doubleFormatForInput(Double number) {
        if (number == null) {
            return "";
        }
        if (number.equals(DOUBLE_ZERO)) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("############.##");
        df.setRoundingMode(RoundingMode.HALF_UP);  
        return df.format(number);
    }
}
