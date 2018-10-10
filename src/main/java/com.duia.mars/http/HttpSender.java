package com.duia.mars.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Http请求发送器
 * @author xingshaofei
 * @date 2018-01-23 下午 3:39
 */
public class HttpSender {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_ENCODING = "UTF-8";

    private HttpClient client;

    private DateFormat dateFormat;


    public HttpSender() {
        this.client = HttpClientBuilder.create().build();
        this.dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }

    public HttpSender(DateFormat dateFormat) {
        this.client = HttpClientBuilder.create().build();
        this.dateFormat = dateFormat;
    }

    public HttpSender(String dateFormatPattern) {
        this.client = HttpClientBuilder.create().build();
        this.dateFormat = new SimpleDateFormat(dateFormatPattern);
    }

    public HttpSender(HttpClient httpClient) {
        this.client = httpClient;
        this.dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }

    public HttpSender(HttpClient httpClient, DateFormat dateFormat) {
        this.client = httpClient;
        this.dateFormat = dateFormat;
    }

    public HttpSender(HttpClient httpClient, String dateFormatPattern) {
        this.client = httpClient;
        this.dateFormat = new SimpleDateFormat(dateFormatPattern);
    }


    public HttpResponseEntity post(String url, Map<String, Object> params) {

        try {

            // 定义uri
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setPath(url);
            URI uri = uriBuilder.build();

            // 定义post
            HttpPost post = new HttpPost(uri);

            // 设置参数
            setPostParameters(post, params);

            // 执行post
            HttpResponse response = client.execute(post);

            // 响应状态码
            int statusCode = response.getStatusLine().getStatusCode();

            // 响应体
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            return new HttpResponseEntity(statusCode, result);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 设置post参数
     * @param post
     * @param paramMap
     */
    private void setPostParameters(HttpPost post, Map<String, Object> paramMap) throws UnsupportedEncodingException {

        if (null == paramMap || paramMap.isEmpty())
            return ;

        List<BasicNameValuePair> params = new ArrayList<>(paramMap.size());

        for (String key : paramMap.keySet()) {

            Object o = paramMap.get(key);

            if (o instanceof Date)
                o = dateFormat.format((Date)o);

            params.add(new BasicNameValuePair(key, o.toString()));
        }

        HttpEntity e = new UrlEncodedFormEntity(params, DEFAULT_ENCODING);

        post.setEntity(e);
    }


}
