package com.duia.mars.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 传输学习数据的历史记录
 */
@Table(name = "transmit_study_data_history")
public class TransmitStudyDataHistory {

	/** 发送结果状态-未发送 */
	public static final int SEND_RESULT_STATUS_UNSEND = 0;
	/** 发送结果状态-成功 */
	public static final int SEND_RESULT_STATUS_SUCCESS = 1;
	/** 发送结果状态-失败 */
	public static final int SEND_RESULT_STATUS_FAILED = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String	dataDate;		 		/* 数据的日期（哪一天的数据） */
	private Integer	totalRecordCount;		/* 数据总记录数 */
	private Integer	totalBatchCount;		/* 数据分批次的总批数 */
	//private Integer	currentBatchNum;		/* 当前为第几批 */
	//private Integer	currentRecordCount;		/* 本批次的记录数 */
	private Integer	currentSendTimes;		/* 发送次数（本次为第几次发送） */
	private Integer	sendDuration;			/* 发送使用的时长，单位毫秒 */
	private Integer	sendResultStatus;		/* 发送的结果的标识码（0：未发送，1：发送成功，2：发送失败） */
	private String	sendResultContent;		/* 发送结果内容 */
	private Date	sendTime;				/* 发送时间 */
	private Date	createTime;				/* 本条记录创建时间 */
	private Date	updateTime;				/* 本条记录更新时间 */

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	/*public Integer getCurrentBatchNum() {
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
	}*/
	public Integer getCurrentSendTimes() {
		return currentSendTimes;
	}
	public void setCurrentSendTimes(Integer currentSendTimes) {
		this.currentSendTimes = currentSendTimes;
	}
	public Integer getSendDuration() {
		return sendDuration;
	}
	public void setSendDuration(Integer sendDuration) {
		this.sendDuration = sendDuration;
	}
	public Integer getSendResultStatus() {
		return sendResultStatus;
	}
	public void setSendResultStatus(Integer sendResultStatus) {
		this.sendResultStatus = sendResultStatus;
	}
	public String getSendResultContent() {
		return sendResultContent;
	}
	public void setSendResultContent(String sendResultContent) {
		this.sendResultContent = sendResultContent;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {

		return "TransmitStudyDataHistory{" +
				"id=" + id +
				", dataDate=" + dataDate +
				", totalRecordCount=" + totalRecordCount +
				", totalBatchCount=" + totalBatchCount +
				", currentSendTimes=" + currentSendTimes +
				", sendDuration=" + sendDuration +
				", sendResultStatus=" + sendResultStatus +
				", sendResultContent='" + sendResultContent + '\'' +
				", sendTime=" + sendTime +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
