package com.duia.mars.common.dto;

import com.duia.mars.model.CrmSaleHandoverNew;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/11.
 */
@Alias("CrmSaleHandoverNewDto")
public class CrmSaleHandoverNewDto extends CrmSaleHandoverNew {
    private int registerChannel;
    private int agentChannelId ;
    private Double money;
    private Date colseTime;
    private int status=1;

    public int getAgentChannelId() {
        return agentChannelId;
    }

    public void setAgentChannelId(int agentChannelId) {
        this.agentChannelId = agentChannelId;
    }

    public int getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(int registerChannel) {
        this.registerChannel = registerChannel;
    }

    public Double getMoney() {
       return super.getCostPrice();
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getColseTime() {
        return colseTime;
    }

    public void setColseTime(Date colseTime) {
        this.colseTime = colseTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
