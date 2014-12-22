package com.xdtech.common.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailUtil {
    public static void send(String userMail,String username,String password) throws Exception {
        JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
        Properties prop = new Properties();
        //默认商城发送用的用户和密码
        javaMail.setUsername("zzxstudynumber");
        javaMail.setPassword("zzxlp522");
        prop.setProperty("mail.smtp.host", "smtp.163.com");
        prop.setProperty("mail.smtp.auth", "true");
        javaMail.setJavaMailProperties(prop);
        MimeMessage message = javaMail.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
        messageHelper.setSubject("感谢您注册农耕食代商城！");
        messageHelper.setSentDate(new Date());
        messageHelper.setFrom("zzxstudynumber@163.com","农耕食代");
        messageHelper.setTo(userMail);
        StringBuffer sb = new StringBuffer();
        sb.append("<html><head><META http-equiv=Content-Type content='text/html; charset=UTF-8'></HEAD><title>农耕食代</title></head><body>")
          .append("<p>亲爱的"+username+"：</p>")
          .append("&nbsp;&nbsp;&nbsp;&nbsp;感谢您注册农耕食代商城<a href='http://localhost:8090/Shopping2'>http://localhost:8090/Shopping2</a>,我们将为您提供最贴心的服务，祝您购物愉快。以下是您的注册信息:<br/>")
          .append("&nbsp;&nbsp;&nbsp;&nbsp;您的用户名："+username+"<br/>")
          .append("&nbsp;&nbsp;&nbsp;&nbsp;您的首次登录密码："+password+"<br/>") 
          .append("&nbsp;&nbsp;&nbsp;&nbsp;登录点达商城购物或查看订单信息需输入您的用户名和密码。建议您保管好本邮件！如忘记密码，请点此找回密码。")
          .append("</body></html>");
        messageHelper.setText(sb.toString(), true);
        javaMail.send(message);
    }
    
    public static void main(String[] args) throws Exception {
    	SendMailUtil.send("358744287@qq.com", "zzx", "qqq");
	}
}
