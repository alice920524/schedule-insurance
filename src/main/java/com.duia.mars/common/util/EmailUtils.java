package com.duia.mars.common.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * Commons email邮箱工具类
 * Created by wangsongpeng on 2016/3/29.
 */

@Component
public class EmailUtils {


    private final static org.slf4j.Logger logger;

    private final static Properties properties = new Properties();//属性对象读取邮件配置文件


    private final static Properties propertiesSSO = new Properties();//sso属性对象读取邮件配置文件

    static {
        logger = LoggerFactory.getLogger(EmailUtils.class);
        try {
            properties.load(EmailUtils.class.getResourceAsStream("/smtp.properties"));
        }catch (IOException e){
            logger.error("加载邮件配置文件smtp.properties时出现异常",e);
        }

    }


    /**
     * 员工添加成功后发送初始化密码到对啊邮箱
     *
     */
    public static void sendExptionEmail(){
        SimpleEmail simpleEmail = new SimpleEmail();
        try{
            if(Integer.parseInt(properties.getProperty("isSend"))==0)
                return;
            simpleEmail.setHostName(properties.getProperty("mail.smtp.host"));//邮件服务器地址
            simpleEmail.setSmtpPort(Integer.valueOf(properties.getProperty("mail.smtp.port")));//端口号
            simpleEmail.setAuthentication(properties.getProperty("mail.smtp.user"),properties.getProperty("mail.smtp.pass"));//邮件用户名密码
            simpleEmail.setFrom(properties.getProperty("mail.smtp.from"));//邮件发送人
            String[] array = properties.getProperty("accept.users").split(";");
            simpleEmail.addTo(array);//邮件接收人
            simpleEmail.setSubject("计算失败");//邮件主题
            simpleEmail.setMsg("啊哦，看到这封邮件表明昨天的财务确认收入数据计算失败了，怎么办呢，上后台（http://sso.back.duia.com）重新计算咯;纳尼？重新调用还不行啊，那就来产品技术部找我们咯~当前时间："+ DateUtils.getNow());//邮件正文
            simpleEmail.send();//发送
        }catch (EmailException e){
             logger.error("发送邮件时,异常",e);
        }
    }


    /**
     * 发送Email
     * <br>发送给配置文件配置的用户
     * @param subject email主题
     * @param content email内容
     */
    public static void sendEmail(String subject, String content) {

        String[] users = properties.getProperty("accept.users").split(";");

        sendEmail(subject, content, users);
    }

    /**
     * 发送Email
     * @param subject email主题
     * @param content email内容
     * @param users 接收email的用户，可多个
     */
    public static void sendEmail(String subject, String content, String ...users) {

        SimpleEmail simpleEmail = new SimpleEmail();

        try {

            if(Integer.parseInt(properties.getProperty("isSend")) == 0)
                return;

            simpleEmail.setHostName(properties.getProperty("mail.smtp.host"));                      //邮件服务器地址
            simpleEmail.setSmtpPort(Integer.valueOf(properties.getProperty("mail.smtp.port")));     //端口号
            simpleEmail.setAuthentication(properties.getProperty("mail.smtp.user"), properties.getProperty("mail.smtp.pass"));  //邮件用户名密码
            simpleEmail.setFrom(properties.getProperty("mail.smtp.from"));                          //邮件发送人

            simpleEmail.addTo(users);           //邮件接收人
            simpleEmail.setSubject(subject);    //邮件主题
            simpleEmail.setMsg(content);        //邮件正文

            simpleEmail.send();                 //发送

        } catch (EmailException e) {
            logger.error("发送邮件时,异常",e);
        }
    }

}
