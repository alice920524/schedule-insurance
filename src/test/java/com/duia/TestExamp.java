package com.duia;

import com.duia.mars.dao.TransmitStudyDataHistoryMapper;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhenghui on 2017/9/5.
 */
public class TestExamp extends Tester {

    @Resource
    private TransmitStudyDataHistoryMapper transmitStudyDataHistoryMapper;

    @Test
    public void test(){
       /* DuiaClient client = DuiaClientFactory.getInstance().getClient("http");
//        String result = client.post("http://sale.rd.back.duia.com/" + "crmSaleHandoverNew/financeCloseSchedule");


        String result = client.post("http://uc.duia.com/test?time="+System.currentTimeMillis());
        result.isEmpty();*/
    }

    private ExclusionStrategy es = new ExclusionStrategy() {

        @Override
        public boolean shouldSkipField(FieldAttributes f) {

            //int fileLength = f.getName().length();

            //return fileLength > 1;
            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {

            return false;
        }
    };


    @Test
    public void testUpdateEmoji() {

        //<!-- '😄' -->

        //String emoji = "\uD83D\uDE04";

        //transmitStudyDataHistoryMapper.updateEmoji(emoji);

    }


}
