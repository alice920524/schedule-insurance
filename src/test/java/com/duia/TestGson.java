package com.duia;/**
 * Created by Administrator on 2017/12/29 0029.
 */

import com.duia.mars.common.util.*;
import com.duia.pojo.StudyDataBean;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author xingshaofei
 * @date 2017-12-29 上午 10:35
 */
public class TestGson {


    Map<String, String> fm = new HashMap<>();

    FieldNamingStrategy fns = new FieldNamingStrategy() {

        @Override
        public String translateName(Field f) {

            String name = fm.get(f.getName());

            if (null == name)
                return f.getName();

            return name;
        }
    };


    @Test
    public void trans() throws UnsupportedEncodingException {

        fm.put("name", "a");    // 名字对应

        GsonBuilder gb = new GsonBuilder();
        Gson g = gb
                .setFieldNamingStrategy(fns)
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        TestSkipFieldBean b = createBean();

        String j = g.toJson(b);

        System.out.println(j);

        String ejs = Base64Util.encodeToString(j);

        System.out.println(ejs);


        String djs = Base64Util.decodeToString(ejs);

        System.out.println(djs);

    }

    private TestSkipFieldBean createBean() {

        TestSkipFieldBean b = new TestSkipFieldBean();

        b.setName("哈哈");
        b.setCreateTime(new Date());

        return b;

    }

    @Test
    public void testGson2() throws UnsupportedEncodingException {

        String data = "[{\"a\": 1,\"b\": \"张三\"}]";

        String s = Base64Util.encodeToString(data);

        System.out.println(s);

    }

    @Test
    public void testUnion() {

        List<StudyDataBean> l1 = new ArrayList();
        List<StudyDataBean> l2 = new ArrayList();
        List<StudyDataBean> l3 = new ArrayList();

        for (int i = 0; i < 100000; i++) {

            StudyDataBean sd = getStudyDataBean(i);
            sd.setId(i);

            l1.add(sd);

            if (i * 2 < 100000) {

                StudyDataBean sd2 = getStudyDataBean(i * 2);
                sd2.setId(i * 2);
                l2.add(sd2);
            }

            if ((i + 1) * 3 < 10000) {

                StudyDataBean sd3 = getStudyDataBean((i + 1) * 3);
                sd3.setId((i + 1) * 3);
                l3.add(sd3);
            }
        }


        long s = System.currentTimeMillis();

        Collection<StudyDataBean> union = CollectUtils.union(l1, l2, l3);

        System.out.println("总数：" + union.size() + "\r\n用时：" + (System.currentTimeMillis() - s) + "ms");

        /*for (StudyDataBean sdb : union) {

            System.out.println(sdb);
        }*/
    }

    public StudyDataBean getStudyDataBean(int i) {

        StudyDataBean sd = new StudyDataBean();

        sd.setStudentId(i);
        sd.setClassNo("classno-" + i);

        sd.setSource("pc");

        sd.setChapterName("第" + i + "章");

        return sd;
    }

    @Test
    public void validateEmoji() {

        Charset charset = Charset.defaultCharset();

        //charset = Charset.forName("UTF-16");

        String emoji = "\uD83D\uDE04";

        byte[] bytes = emoji.getBytes(charset);

        for (byte b : bytes) {
            System.out.println(b);
        }

        System.out.println("字节数：" + bytes.length);
    }

    @Test
    public void testCalendar() {

        Calendar c = Calendar.getInstance();

        System.out.println(DateUtils.format(c.getTime(), DateUtils.FORMAT_ONE));

        c.add(Calendar.SECOND, -60);
        Date time = c.getTime();

        System.out.println(DateUtils.format(time, DateUtils.FORMAT_ONE));
    }

    @Test
    public void testCompressAndBase64() throws IOException {

        String str = "[\n" +
                "\t{\n" +
                "\t\t\"a\": 1,\n" +
                "\t\t\"b\": \"CJ-GX8171101-39\",\n" +
                "\t\t\"c\": 70,\n" +
                "\t\t\"d\": 277,\n" +
                "\t\t\"e\": \"初级会计-高薪就业班(直播)\",\n" +
                "\t\t\"f\": \"会计初级职称171101\",\n" +
                "\t\t\"g\": \"初级会计实务\",\n" +
                "\t\t\"h\": 38791,\n" +
                "\t\t\"i\": \"第六节 长期借款、第七节 应付债券及长期应付款\",\n" +
                "\t\t\"j\": \"2017-12-01 19:00\",\n" +
                "      \t\"k\": \"2017-12-01 22:00\",\n" +
                "\t\t\"l\": \"WEB\",\n" +
                "\t\t\"m\": \"2017-12-01 19:01:26\",\n" +
                "\t\t\"n\": \"2017-12-01 22:07:39\"\n" +
                "  }\n" +
                "]";
        str = "[{\"a\": 1,\"b\": \"张三\"}]";

        byte[] bytes = str.getBytes("utf-8");
        print(bytes);

        byte[] compress = CompressUtils.compressByDeflater(str, "utf-8");

        print(compress);

        //String cs = new String(compress, "utf-8");

        //String es = Base64Util.encodeToString(cs, "utf-8");

        String es = Base64Util.encodeToString(compress, "utf-8");

        System.out.println(es);

    }


    @Test
    public void testUnBase64AndCompress() throws IOException {

        byte[] eb = {120, -100, -53, 40, 125, -38, 63, -15, -23, -46, -23, 0, 21, 120, 5, 4};

        String encryptStr = "eJyLrlZKVLJSMNRRSgJSSk/3LHiyo1OpNhYAW2MItw==";

        eb = Base64Util.decodeToBytes(encryptStr, "utf-8");

        String us = CompressUtils.uncompressByInflater(eb, "utf-8");

        //String us = new String(uncompress, "utf-8");

        System.out.println(us);

    }

    private void print(byte[] bs) {

        StringBuilder sb = new StringBuilder();

        for (byte b : bs) {

            sb.append(b);
            sb.append(", ");
        }

        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }

    @Test
    public void sendEmail() {

        EmailUtils emailUtils = new EmailUtils();
        emailUtils.sendEmail("a", "aaa");
    }


    @Test
    public void testEncode() {

        byte[] bs = {1, 2, 3, 4};

        int i = byteArrayToInt(bs);

        System.out.println(i);

        byte[] bs2 = intToByteArray(i);

        print(bs2);

    }

    public static int byteArrayToInt(byte[] bs) {

        return ByteUtils.toInt(bs);
    }

    public static byte[] intToByteArray(int i) {

        return ByteUtils.toByteArray(i);
    }

}
