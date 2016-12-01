package com.spider.util;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by su on 2016/3/21.
 */
public class Http
{

    /**
     * 日志
     */
    private static Logger log = Logger.getLogger(Http.class);
    /**
     * 用于后续访问许可验证cookie
     */
    public static Map<String, String> m_cookies = null;// cookie
    /**
     * 模拟浏览器头
     */
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0";
    /**
     * 定义全局HTTP文件头
     */
    private static Map<String, String> m_headerMap = new HashMap<String, String>()
    {
        {
            // put("GET", "/dito/ditoAction!ycmlFrame.dhtml HTTP/1.1");
            // put("Host", "qyxy.baic.gov.cn");
            put("Connection", "keep-alive");
            put("Cache-Control", "max-age=0");
            put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            put("Upgrade-Insecure-Requests", "1");
            put("User-Agent", userAgent);
            put("Accept-Encoding", "gzip, deflate, sdch");
            put("Accept-Language", "zh-CN,zh;q=0.8");
        }
    };

    public static void main(String[] args)
    {
    }

    /**
     * 通过特定URL获取访问许可cookie
     * @param url 获取cookie URL
     * @param paramsMap URL后缀参数哈希表
     * @param headerMap HTTP文件头状态位哈希表
     * @return Map<String, String>
     */
    public static Map<String, String> getCookies(String url, Map paramsMap, Map<String, String> headerMap)
    {
        // 休眠指定时间
        sleepTime();
        try
        {
            // 第一次请求
            Connection connect = Jsoup.connect(url + getUrlParamSuffix(paramsMap));// 获取连接
            // 加载缺省HTTP头
            for (String key : m_headerMap.keySet())
            {
                connect.header(key, m_headerMap.get(key));
            }
            // 设置自定义HTTP Header
            if (headerMap != null)
            {
                for (String key : headerMap.keySet())
                {
                    connect.header(key, headerMap.get(key));
                }
            }
            // 超时设置为5分钟
            connect.timeout(5 * 60 * 1000);
            // 获取响应
            Connection.Response res = connect.method(Connection.Method.GET).execute();
            // 获取访问的cookie,下面用到,不然访问不了内页
            // Map<String, String> cookies = res.cookies();

            return res.cookies();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 用GET方式获取指定URL返回内容
     * @param url 指定URL
     * @param paramsMap URL后缀参数哈希表
     * @param headerMap HTTP头状态位哈希表
     * @param cookies 访问cookie
     * @return String
     */
    public static String httpGetSpider(String url, Map<String, String> paramsMap,
                                       Map<String, String> headerMap, Map<String, String> cookies)
    {
        // 休眠指定时间
        sleepTime();
        try
        {
            // 获取连接
            Connection connect = Jsoup.connect(url + getUrlParamSuffix(paramsMap));
            // 设置cookie
            if (cookies != null)
            {
                connect.cookies(cookies);
            }
            // 加载缺省HTTP头
            for (String key : m_headerMap.keySet())
            {
                connect.header(key, m_headerMap.get(key));
            }
            // 设置自定义HTTP Header
            if (headerMap != null)
            {
                for (String key : headerMap.keySet())
                {
                    connect.header(key, headerMap.get(key));
                }
            }
            // 设置响应超时时间，5分钟
            connect.timeout(5 * 60 * 1000);
            // 获取响应
            Connection.Response resp = connect.method(Connection.Method.GET).execute();
            m_cookies = resp.cookies(); // 同步赋值至cookie对象
            // String responseText = resp.body();

            return resp.body();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 用POST方式获取指定URL返回内容
     * @param url 访问指定URL
     * @param paramsMap POST提交参数哈希表
     * @param headerMap HTTP头状态位哈希表
     * @param cookies 访问cookie
     * @return String
     */
    public static String httpPostSpider(String url, Map<String, String> paramsMap,
                                        Map<String, String> headerMap, Map<String, String> cookies)
    {
        // 休眠指定时间
        sleepTime();
        try
        {
            // 获取连接
            Connection connect = Jsoup.connect(url);
            // 设置cookie
            if (cookies != null)
            {
                connect.cookies(cookies);
            }
            // 加载缺省HTTP头
            for (String key : m_headerMap.keySet())
            {
                connect.header(key, m_headerMap.get(key));
            }
            // 设置自定义HTTP Header
            if (headerMap != null)
            {
                for (String key : headerMap.keySet())
                {
                    connect.header(key, headerMap.get(key));
                }
            }
            // 设置User-Agent
            // connect.header("User-Agent", userAgent);
            // 设置响应超时时间，5分钟
            connect.timeout(5 * 60 * 1000);
            //
            connect.data(paramsMap);// **
            connect.ignoreContentType(true);// 忽略返回内容文本类型
            // 获取响应
            Connection.Response resp = connect.method(Connection.Method.POST).execute();

            // String responseText = resp.body();

            return resp.body();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 休眠时间，时间数据由配置文件中引入，单位为秒
     */
    public static void sleepTime()
    {
        try
        {
            log.info("sleep " + ConfigHelper.getParamValue("interval") + "s");
            Thread.sleep(Integer.parseInt(ConfigHelper.getParamValue("interval")) * 1000l);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 根据URL后缀中拼装参数值对解析指定字段值
     * @param urlParams 拼装URL后缀参数值对字符串
     * @param tag 分隔符
     * @param key 参数名
     * @return 返回参数值
     */
    public static String getValueOfParamsString(String urlParams, String tag, String key)
    {
        String value = null;
        String[] urlArray = urlParams.split(tag);
        for (String param : urlArray)
        {
            if (param.startsWith(key))
            {
                value = param.split("=")[1];
                break;
            }
        }

        return value;
    }

    /**
     * 根据初始化POST参数拼装URL字符串后缀 用途：用于自动化采集时进行排重
     * @param map 参数组合
     * @return 返回参数组合字符串
     */
    public static String getUrlParamSuffix(Map<String, String> map)
    {
        String str = "";
        int k = 0;
        if (map != null)
        {
            for (String key : map.keySet())
            {
                // if (!key.equals("tmp")) {
                str += k == 0 ? ("?" + key + "=" + map.get(key)) : ("&" + key + "=" + map.get(key));
                // }
                k++;
            }
        }

        return str;
    }

    /**
     * 剔除字符串中空格、换行、回车
     * @param str
     * @return
     */
    public static String replaceBlankLineTab(String str)
    {
        return str.replaceAll("\\s*|\t|\r|\n", "");
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj)
    {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[])
        {
            Object[] object = (Object[]) obj;
            if (object.length == 0)
            {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++)
            {
                if (!isNullOrEmpty(object[i]))
                {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
