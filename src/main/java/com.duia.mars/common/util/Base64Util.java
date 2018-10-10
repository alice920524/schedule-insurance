package com.duia.mars.common.util;/**
 * Created by Administrator on 2017/12/29 0029.
 */

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Base64加解密
 * @author xingshaofei
 * @date 2017-12-29 下午 2:06
 */
public class Base64Util {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 将参数以"UTF-8"格式进行Base64编码，输出Base64字符串
     * @param source 要被编码的字符串，此字符串会被按照"UTF-8"解析为字节后转为Base64形式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeToString(String source) throws UnsupportedEncodingException {

        return encodeToString(source, DEFAULT_CHARSET);
    }

    /**
     * 将参数进行Base64编码，输出Base64字符串
     * @param source 要被编码的字符串
     * @param charSet 指定被编码的字符串按照什么字符集解析为字节
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeToString(String source, String charSet) throws UnsupportedEncodingException {

        byte[] sourceBytes = source.getBytes(charSet);

        return Base64.encodeBase64String(sourceBytes);
    }

    /**
     * 将参数以"UTF-8"格式进行Base64编码，输出Base64字符串
     * @param source 要被编码的字符串，此字符串会被按照"UTF-8"解析为字节后转为Base64形式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeToString(byte[] source) throws UnsupportedEncodingException {

        return encodeToString(source, DEFAULT_CHARSET);
    }

    /**
     * 将参数进行Base64编码，输出Base64字符串
     * @param source 要被编码的字符串
     * @param charSet 指定被编码的字符串按照什么字符集解析为字节
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeToString(byte[] source, String charSet) throws UnsupportedEncodingException {

        return Base64.encodeBase64String(source);
    }

    /**
     * 将参数进行Base64解码，返回解码后字节以"UTF-8"作为字符集转为的字符串
     * @param source 要被解码的Base64字符串
     * @return 解码后的字节以"UTF-8"作为字符集转为的字符串
     * @throws UnsupportedEncodingException
     */
    public static String decodeToString(String source) throws UnsupportedEncodingException {

        return decodeToString(source, DEFAULT_CHARSET);
    }

    /**
     * 将参数进行Base64解码
     * @param source 要被解码的Base64字符串
     * @param charSet 指定解码后的字节以什么字符集转为字符串
     * @return 以指定字符集转为的字符串
     * @throws UnsupportedEncodingException
     */
    public static String decodeToString(String source, String charSet) throws UnsupportedEncodingException {

        byte[] sourceBytes = Base64.decodeBase64(source);

        return new String(sourceBytes, charSet);
    }

    /**
     * 将参数进行Base64解码，返回解码后字节以"UTF-8"作为字符集转为的字符串
     * @param source 要被解码的Base64字符串
     * @return 解码后的字节以"UTF-8"作为字符集转为的字符串
     * @throws UnsupportedEncodingException
     */
    public static byte[] decodeToBytes(String source) throws UnsupportedEncodingException {

        return decodeToBytes(source, DEFAULT_CHARSET);
    }

    /**
     * 将参数进行Base64解码
     * @param source 要被解码的Base64字符串
     * @param charSet 指定解码后的字节以什么字符集转为字符串
     * @return 以指定字符集转为的字符串
     * @throws UnsupportedEncodingException
     */
    public static byte[] decodeToBytes(String source, String charSet) throws UnsupportedEncodingException {

        return Base64.decodeBase64(source);
    }

}
