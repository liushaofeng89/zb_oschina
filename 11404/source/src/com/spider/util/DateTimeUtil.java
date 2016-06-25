package com.spider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期格式化工具
 * @author liushaofeng
 * @date 2016-6-7 下午08:06:21
 * @version 1.0.0
 */
public final class DateTimeUtil
{
    /**
     * 转换字符串日期格式为long类型日期
     * @param str 待转换的字符串日期
     * @param dateType 日期格式
     * @return long类型的日期
     */
    public static Long convert2Long(String str, String dateType)
    {
        if (null == str || null == dateType || dateType.isEmpty() || str.isEmpty())
        {
            throw new IllegalArgumentException("Input parameter should not be null or empty!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateType);
        try
        {
            Date parse = sdf.parse(str);
            return parse.getTime();
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
