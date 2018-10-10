package com.duia;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 简单的普通测试
 * @author xingshaofei
 * @date 2018-02-01 下午 2:27
 */
public class TestSimple {
    
    private static final Logger log = LoggerFactory.getLogger(TestSimple.class);

    @Test
    public void test() throws InterruptedException {

        String emoji = "\uD83D\uDE04";
        String s = "汉字";

        for (int i = 0; i < 1_000; i++) {

            Thread.sleep(5_000);

            log.info("s:{}, emoji:{}", s, emoji);
        }

    }


    @Test
    public void testQuestionWordFile() throws IOException {

        String filePath = "D://temp//logback-info.2018-01-29.log";

        File file = new File(filePath);

        FileInputStream fis = new FileInputStream(file);

        byte[] bs = new byte[100];

        int readCount = fis.read(bs);

        log.info("read count:{}", readCount);

        print(bs);

    }


    private void print(byte[] bs) {

        StringBuilder sb = new StringBuilder();

        for (byte b : bs) {
            sb.append(b + ",");
        }

        log.info(sb.toString());
    }

}
