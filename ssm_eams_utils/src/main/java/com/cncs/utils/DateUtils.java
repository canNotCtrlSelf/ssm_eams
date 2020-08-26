package com.cncs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具
 */
public class DateUtils {

    /**
     * Date转为String
     * @param date  需要进行转换的日期
     * @param pattern 转换的格式
     * @return
     */
    public static String date2String(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * String转换为Date
     * @param date  需要进行解析并生成Date类型的字符串
     * @param pattern   解析的格式
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String date,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse(date);
        return parse;
    }
}
