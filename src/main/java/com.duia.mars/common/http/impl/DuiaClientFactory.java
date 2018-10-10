package com.duia.mars.common.http.impl;


import com.duia.mars.common.http.DuiaClient;

/**
 * Created by chenqi on 2016/11/23.
 */
public class DuiaClientFactory {

    private static DuiaClientFactory factory;

    public static DuiaClientFactory getInstance(){
        if(factory == null){
            factory = new DuiaClientFactory();
        }
        return factory;
    }

    public DuiaClient getClient(String way){
        if(way != null && way.equals("https")){
            return new HttpsClient();
        }
        return new HttpClient();
    }

}
