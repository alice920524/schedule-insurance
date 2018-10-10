package com.duia.mars.common.http.impl;

import com.duia.mars.common.http.DuiaClient;
import com.duia.mars.common.http.util.HttpTool;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HTTP工具类.
 *
 * @author David.Huang
 */
public class HttpClient implements DuiaClient {

    protected HttpClient(){}

    private static Logger log = LoggerFactory.getLogger(HttpClient.class);

    /** 默认编码方式 -UTF8 */
    private static final String DEFAULT_ENCODE = "utf-8";
    private static String signKey = "signature";

    public String post(String url){
        return post(url,null,null,null);
    }

    public String post(String url,Map<String,String> params){
        return post(url,params,null,null);
    }

    public String post(String url,Map<String,String> params,Map<String,String> headers){
        return post(url,params,headers,null);
    }

    public String post(String url,Map<String,String> params,Map<String,String> headers,String payload){
        String result = null;
        try {
            if(url == null || !url.matches("^https?:\\/\\/.*$")){
                throw new NullPointerException("请录入正确的URL");
            }
            url = url.replaceFirst("^https?","http");
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            org.apache.http.client.HttpClient client = httpClientBuilder.build();
            URIBuilder builder = new URIBuilder();
            builder.setPath(url);
            StringBuffer sb = new StringBuffer();
            if(params == null){
                params = new HashMap<String,String>();
                params.put("timestamp",String.valueOf(System.nanoTime()));
            }
            if(params.size() > 0){
                Set<String> keys = params.keySet();
                Iterator<String> its = keys.iterator();
                while (its.hasNext()){
                    String key = its.next();
                    String value = params.get(key);
                    builder.setParameter(key,value);
                }
            }
            builder.setParameter(signKey, HttpTool.signature(params));
            URI rui = builder.build();
            HttpPost post = new HttpPost(rui);
            if(payload != null){
                StringEntity stringEntity = new StringEntity(payload,"utf-8");
                post.setEntity(stringEntity);
            }
            if(headers != null && headers.size() > 0){
                Set<String> keys = headers.keySet();
                Iterator<String> its = keys.iterator();
                while (its.hasNext()){
                    String key = its.next();
                    String value = headers.get(key);
                    Header header = new BasicHeader(key,value);
                    post.addHeader(header);
                }
            }
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }

}
