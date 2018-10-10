package com.duia.mars.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 传输学习数据-正课直播
 */
@Document(collection = "transmit_track_class_course_live_transmit-pro20180615")   // todo for save send to pro data
//@Document(collection = "transmit_track_class_course_live_transmit-test20180615")    // todo for save send to test data
public class TrackClassCourseLiveTransmitMean extends TransmitStudyDataMean {

}
