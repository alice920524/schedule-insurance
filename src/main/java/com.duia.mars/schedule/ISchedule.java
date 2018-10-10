package com.duia.mars.schedule;

/**
 * Created by chenqi on 2016/8/19.
 */

public interface ISchedule {

    public void schedule();

    public void restore(Integer scheduleId);
}