package cn.xiaosm.yadmin.util;

import cn.xiaosm.yadmin.exception.CanShowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * 邮件发送工具类
 *
 * @author Young
 * @create 2020/6/28
 * @since 1.0.0
 */
@Slf4j
@Component
public class MailUtils {

    private static String FROM_ADDRESS = "";
    private static String FROM_NAME = "Plain-admin";

    private static JavaMailSender javaMailSender;

    @Autowired
    public MailUtils(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public static void sendMail(String to, String subject, String content) {
        MailUtils.sendMail(to, subject, content, false);
    }

    /**
     * 发送普通邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param isHtml 是否以html形式发送
     */
    public static void sendMail(String to, String subject, String content, boolean isHtml) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(FROM_ADDRESS, FROM_NAME);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, isHtml);
            javaMailSender.send(message);
            log.info("邮件发送成功，接受方=>[{}]", to);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("发送简单邮件时出现异常!", e);
            throw new CanShowException("邮件发送出现错误###详情请查看异常日志信息");
            // ResponseUtils.sendError();
        }
    }

    public static void updateMail() {

    }

    public static void setFromAddress(String fromAddress) {
        MailUtils.FROM_ADDRESS = fromAddress;
    }

    public static void setFromName(String fromName) {
        MailUtils.FROM_NAME = fromName;
    }
}