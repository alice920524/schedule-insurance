package com.duia.mars.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 * 压缩工具类
 * @author xingshaofei
 * @date 2018-01-19 下午 6:48
 */
public class CompressUtils {

    /**
     * 压缩文本
     * @param source 要被压缩的文本，如果为空，则返回null
     * @param encoding 文本编码方式
     * @return 压缩后的字节
     * @throws IOException
     */
    public static byte[] compressByGzip(String source, String encoding) throws IOException {

        if (source == null || source.length() == 0)
            return null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip;

        gzip = new GZIPOutputStream(out);

        gzip.write(source.getBytes(encoding));

        gzip.close();

        return out.toByteArray();
    }

    /**
     * 压缩文本
     * @param source 要被压缩的文本，如果为空，则返回null
     * @param encoding 文本编码方式
     * @return 压缩后的字节
     * @throws IOException
     */
    public static byte[] compressByDeflater(String source, String encoding) throws IOException {

        if (source == null || source.length() == 0)
            return null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Deflater compressor = new Deflater();

        compressor.setInput(source.getBytes(encoding));
        compressor.finish();

        byte[] buf = new byte[1024];

        while (!compressor.finished()) {

            int i = compressor.deflate(buf);
            out.write(buf, 0, i);
        }

        compressor.end();

        out.close();

        return out.toByteArray();

    }

    /**
     * 解压数据，使用inflater（gzip）
     * @param source 要被解压的数据，如果为空，则返回null
     * @param encoding 文本编码方式
     * @return 解压后的字节按文本编码方式转为的字符串
     * @throws IOException
     */
    public static String uncompressByInflater(byte[] source, String encoding) throws IOException {

        if (source == null || source.length == 0)
            return null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Inflater depressor = new Inflater();

        depressor.setInput(source);

        depressor.finished();

        byte[] buf = new byte[1024];

        while (!depressor.finished()) {

            try {
                int i = depressor.inflate(buf);
                out.write(buf, 0, i);
            } catch (DataFormatException e) {
                throw new IOException(e);
            } finally {
                out.close();
            }
        }

        depressor.end();

        return new String(out.toByteArray(), encoding);

    }

}
