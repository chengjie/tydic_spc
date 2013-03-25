package com.tydic.spc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String encodeParamValue(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 批量替换
     *
     * @param source 源字符串
     * @param with   替换字符串
     * @param repls  需替换的字符串列表
     * @return 替换后的字符串
     */
    public static String replaceStr(String source, String with, String... repls) {
        for (String repl : repls) {
            source = replace(source, repl, with);
        }

        return source;
    }

    /**
     * ip校验
     *
     * @param s s
     * @return true or false
     */
    public static Boolean isIpAddress(String s) {
        String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }

}
