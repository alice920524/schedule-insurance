package com.duia.mars.mongodb.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 传输学习数据的记录
 */
public class TransmitStudyDataMean implements Serializable {

	@Id
	private String id;

	private String	dataDate;		 		/* 数据的日期（哪一天的数据） */
	private Integer	totalRecordCount;		/* 数据总记录数 */
	private Integer	totalBatchCount;		/* 数据分批次的总批数 */
	private Integer	currentBatchNum;		/* 当前为第几批 */
	private Integer	currentRecordCount;		/* 本批次的记录数 */

	private String	data;					/* 保存的数据 */
	private byte[]	compressedData;			/* 压缩后的数据 */
	private String	encryptData;			/* 加密后的数据 */

	private Date	createTime;				/* 本条记录创建时间 */


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
}
