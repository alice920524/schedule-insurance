package com.duia.mars.mongodb.dao;

import com.duia.mars.mongodb.model.TrackClassCourseLiveTransmitMean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * Mongo：操作存放[传输学习数据-正课直播]的文档集合
 * @author xingshaofei
 * @date 2017-11-29 下午 3:00
 */
@EnableMongoRepositories
public interface TrackClassCourseLiveTransmitRepository extends MongoRepository<TrackClassCourseLiveTransmitMean, String> {

    @Query
    List<TrackClassCourseLiveTransmitMean> findByDataDate(String dataDate);

}
