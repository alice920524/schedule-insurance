package com.duia.mars.common.http;

import java.util.Map;

/**
 * Created by chenqi on 2016/11/23.
 */
public interface DuiaClient {

    public String post(String url);

    public String post(String url, Map<String, String> params);

    public String post(String url, Map<String, String> params, Map<String, String> headers);

    public String post(String url, Map<String, String> params, Map<String, String> headers, String payload);

}
