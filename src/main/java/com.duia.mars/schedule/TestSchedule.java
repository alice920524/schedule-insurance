package com.duia.mars.schedule;

import com.duia.mars.dao.TransmitStudyDataHistoryMapper;
import com.duia.mars.model.TransmitStudyDataHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 传输学习数据的调度
 */
@Component
@Configurable
public class TestSchedule implements ISchedule {

    private static final Logger log = LoggerFactory.getLogger(TestSchedule.class);

    @Resource
    private TransmitStudyDataHistoryMapper transmitStudyDataHistoryMapper;

    @Override
    @Scheduled(cron = "0/30 * 17 5 2 ?")
    public void schedule() {

        try {

            test();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void test() throws InterruptedException {

        String emoji = "\uD83D\uDE04";
        String s = "汉字";

        for (int i = 0; i < 1_000; i++) {

            Thread.sleep(5_000);

            log.info("s:{}, emoji:{}", s, emoji);
        }

    }

    public void testUpdateEmoji() {

        String emoji = "\uD83D\uDC36";

        print(emoji.getBytes());

        transmitStudyDataHistoryMapper.updateEmoji(emoji);

        TransmitStudyDataHistory history = transmitStudyDataHistoryMapper.selectByPrimaryKey(1);

        print(history.getSendResultContent().getBytes());
    }

    private void print(byte[] bs) {

        StringBuilder sb = new StringBuilder();

        for (byte b : bs) {
            sb.append(b + ",");
        }

        System.out.println(sb.toString());
    }

    @Override
    public void restore(Integer scheduleId) {

        log.info("传输学习数据调度不可进行恢复操作！");
    }

}
