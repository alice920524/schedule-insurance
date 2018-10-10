package com.duia.mars.dto;

import com.duia.mars.common.util.DateUtils;

import java.util.Date;

/**
 * 传输学习数据
 * @author xingshaofei
 * @date 2017-12-27 下午 6:49
 */
public class TransmitStudyData {

    private String  dataDate;		 		/* 数据的日期（哪一天的数据） */

    private Integer	totalRecordCount;		/* 数据总记录数 */
    private Integer	totalBatchCount;		/* 数据分批次的总批数 */
    private Integer	currentBatchNum;		/* 当前为第几批 */
    private Integer	currentRecordCount;		/* 本批次的记录数 */

    private Date	sendTime;				/* 发送时间 */

    private String	data;					/* 数据 */
    private byte[]  compressedData;         /* 压缩后的数据 */
    private String  encryptData;            /* 加密后的数据 */

    private String  signature;              /* 对参数进行加密后的签名 */

    public String getDataDate() {
        return dataDate;
    }
    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }
    public void setTotalRecordCount(Integer totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }
    public Integer getTotalBatchCount() {
        return totalBatchCount;
    }
    public void setTotalBatchCount(Integer totalBatchCount) {
        this.totalBatchCount = totalBatchCount;
    }
    public Integer getCurrentBatchNum() {
        return currentBatchNum;
    }
    public void setCurrentBatchNum(Integer currentBatchNum) {
        this.currentBatchNum = currentBatchNum;
    }
    public Integer getCurrentRecordCount() {
        return currentRecordCount;
    }
    public void setCurrentRecordCount(Integer currentRecordCount) {
        this.currentRecordCount = currentRecordCount;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public byte[] getCompressedData() {
        return compressedData;
    }
    public void setCompressedData(byte[] compressedData) {
        this.compressedData = compressedData;
    }
    public String getEncryptData() {
        return encryptData;
    }
    public void setEncryptData(String encryptData) {
        this.encryptData = encryptData;
    }

    @Override
    public String toString() {

        return "TransmitStudyData{" +
                "dataDate='" + dataDate + '\'' +
                ", totalRecordCount=" + totalRecordCount +
                ", totalBatchCount=" + totalBatchCount +
                ", currentBatchNum=" + currentBatchNum +
                ", currentRecordCount=" + currentRecordCount +
                ", sendTime=" + DateUtils.format(sendTime, DateUtils.FORMAT_ONE) +
                //", data='" + data + '\'' +
                ", compressedData='" + compressedData + "\'" +
                ", encryptData='" + encryptData + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
