package com.ucstu.guangbt.djzhaopin.utils;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageUtil {

    @Resource
    private JavaMailSender mailSender; // 获取邮件发送器

    @Value("${spring.mail.username}") // 获取邮件发送者
    private String from;

    @Async("asyncTaskExecutor") // 异步请求发送邮件
    // 向用户发送电子邮件。
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); // 创建邮件对象
        simpleMailMessage.setFrom(from); // 设置邮件发送者
        simpleMailMessage.setTo(to); // 设置邮件接收者
        simpleMailMessage.setSubject(subject); // 设置邮件主题
        simpleMailMessage.setText(content); // 设置邮件内容
        mailSender.send(simpleMailMessage); // 发送邮件
    }

}
