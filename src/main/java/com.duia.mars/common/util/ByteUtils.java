package com.duia.mars.common.util;

/**
 * byte相关工具类
 * @author xingshaofei
 * @date 2018-01-31 下午 5:12
 */
public class ByteUtils {

    /**
     * byte数组转为int
     * @param bytes byte数组，只转前4个byte
     * @return 转成的int
     */
    public static int toInt(byte[] bytes) {

        int b1 = (bytes[0] & 0xFF) << 24;
        int b2 = (bytes[1] & 0xFF) << 16;
        int b3 = (bytes[2] & 0xFF) << 8;
        int b4 = (bytes[3] & 0xFF);

        return b1 | b2 | b3 | b4;
    }

    /**
     * int转为byte数组
     * @param i 要被转为int
     * @return byte数组，含4个byte
     */
    public static byte[] toByteArray(int i) {

        byte b1 = (byte)(i >> 24 & 0xFF);
        byte b2 = (byte)(i >> 16 & 0xFF);
        byte b3 = (byte)(i >> 8 & 0xFF);
        byte b4 = (byte)(i & 0xFF);

        return new byte[] {b1, b2, b3, b4};
    }

}
