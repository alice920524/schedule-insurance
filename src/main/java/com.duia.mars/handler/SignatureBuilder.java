package com.duia.mars.handler;

import com.duia.mars.common.util.MD5;

import java.util.Map;
import java.util.TreeMap;

/**
 * 签名builder
 * @author xingshaofei
 * @date 2018-01-23 下午 5:45
 */
public abstract class SignatureBuilder {

    /**
     * 指定签名key
     * @return 签名key，不可为空
     */
    public abstract String getSignatureKey();


    /**
     * build签名
     * @param map 参数集合
     * @return 签名
     */
    public String build(Map<String, String> map) {

        String signatureKey = getSignatureKey();

        if (null == map || map.isEmpty())
            throw new NullPointerException("参数不可为空");
        if (null == signatureKey || signatureKey.isEmpty())
            throw new NullPointerException("需设置有效的签名key");

        TreeMap<String, String> treeMap;

        if (map instanceof TreeMap) {
            treeMap = (TreeMap)map;
        } else {
            treeMap = new TreeMap<>();
            treeMap.putAll(map);
        }

        // get and build param
        StringBuilder paramSb = new StringBuilder();

        boolean notFirst = false;
        for(Map.Entry<String, String> entry : treeMap.entrySet()) {

            if(notFirst)
                paramSb.append("&");
            else
                notFirst = true;

            paramSb.append(entry.getKey());

            paramSb.append("=");

            paramSb.append(entry.getValue());
        }

        String withSaleParam = paramSb.append(signatureKey).toString();	// 加了盐的参数拼接

        // build signature

        return MD5.getMD5(withSaleParam);				                // 签名
    }

}
