package com.duia.mars.common.http.impl;

import com.duia.mars.common.http.DuiaClient;
import com.duia.mars.common.http.util.HttpTool;
import org.apache.http.Consts;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.CharArrayBuffer;

import javax.net.ssl.*;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenqi on 2016/11/22.
 */
public class HttpsClient implements DuiaClient {

    protected HttpsClient(){}
    private static String signKey = "signature";

    private class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }

    private class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    @Override
    public String post(String url) {
        return post(url,null);
    }

    @Override
    public String post(String url, Map<String, String> params) {
        return post(url,params,null,null);
    }

    @Override
    public String post(String url, Map<String, String> params, Map<String, String> headers) {
        return post(url,params,headers,null);
    }

    @Override
    public String post(String url, Map<String, String> params, Map<String, String> headers, String payload) {
        SSLContext sc = null;
        try {
            if(url == null || !url.matches("^https?:\\/\\/.*$")){
                throw new NullPointerException("请录入正确的URL");
            }
            url = url.replaceFirst("^https?","https");
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                    new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setDoOutput(true);
            conn.connect();
            URIBuilder builder = new URIBuilder();
            if(params == null){
                params = new HashMap<String,String>();
                params.put("timestamp",String.valueOf(System.nanoTime()));
            }
            Set<String> keys = params.keySet();
            Iterator<String> its = keys.iterator();
            while (its.hasNext()){
                String key = its.next();
                String value = params.get(key);
                builder.setParameter(key,value);
            }
            builder.setParameter(signKey, HttpTool.signature(params));
            URI rui = builder.build();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(rui.getQuery().getBytes(Consts.UTF_8));
//            if(payload != null){
//                out.write(payload.getBytes(Consts.UTF_8));
//            }
            // 刷新、关闭
            out.flush();
            out.close();
            InputStream is = conn.getInputStream();
            return getContext(is, Consts.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getContext(InputStream instream,Charset charset) throws IOException {
        if (instream == null) {
            return null;
        }
        try {
            Reader reader = new InputStreamReader(instream, charset);
            CharArrayBuffer buffer = new CharArrayBuffer(instream.available());
            char[] tmp = new char[1024];
            int l;
            while((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.close();
        }
    }

}
