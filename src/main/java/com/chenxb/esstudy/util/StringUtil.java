package com.chenxb.esstudy.util;

/**
 * @author: chenxb
 * @date: 2018/08/17
 * @desc:
 */
public class StringUtil {

    public static boolean isEmpty(String value) {
        if (value == null || value.length() == 0) {
            return true;
        }
        return false;
    }
}
