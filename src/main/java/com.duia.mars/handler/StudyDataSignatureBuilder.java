package com.duia.mars.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 学习数据签名builder
 * @author xingshaofei
 * @date 2018-01-23 下午 5:45
 */
@Component
public class StudyDataSignatureBuilder extends SignatureBuilder {

    @Value("${send.study-data.signature-key}")
    private String signatureKey;

    @Override
    public String getSignatureKey() {

        return signatureKey;
    }

}
