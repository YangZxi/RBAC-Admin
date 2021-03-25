/**
 * Copyright: 2019-2020
 * FileName: MailConfig
 * Author:   Young
 * Date:     2020/6/28 20:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.util.mail;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.xiaosm.yadmin.basic.entity.Prop;
import cn.xiaosm.yadmin.basic.service.PropService;
import cn.xiaosm.yadmin.basic.util.mail.MailUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.InitBinder;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/28
 * @since 1.0.0
 */
@Configuration
public class MailConfig extends MailProperties {

    @Autowired
    PropService propService;

    JavaMailSenderImpl sender = null;

    @Bean
    JavaMailSenderImpl createJavaMailSender() {
        sender = new JavaMailSenderImpl();
        return sender;
    }

    protected void updateMail() {
        List<Prop> propList = propService.list(new QueryWrapper<Prop>().eq("type", "email"));
        Map<String, String> map = new HashMap<>();
        for (Prop prop : propList) {
            map.put(prop.getPropKey(), prop.getPropValue());
        }
        // Springboot
        sender.setUsername(map.get("email_username"));
        sender.setPassword(map.get("email_password"));
        sender.setHost(map.get("email_server_host"));
        sender.setPort(Integer.valueOf(map.get("email_server_port")));
        // sender.setProtocol("");
        sender.setDefaultEncoding("UTF-8");
        MailUtils.setFromAddress(sender.getUsername());
        MailUtils.setFromName(map.get("email_send_name"));
        // 设置是否加密连接
        this.setMailSsl(sender.getPort(), Boolean.valueOf(map.get("email_ssl")));
    }

    private void setMailSsl(int port, Boolean isSsl) {
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        // ssl 连接，如果不需要请注释以下两行
        if (isSsl) {
            prop.put("mail.smtp.socketFactory.port", port);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        sender.setJavaMailProperties(prop);

        // Hutool
        // MailAccount mailAccount = SpringContextUtils.getBean(MailAccount.class);
        // mailAccount.setUser(map.get("email_username"));
        // mailAccount.setPass(map.get("email_password"));
        // mailAccount.setHost(map.get("email_server_host"));
        // mailAccount.setPort(Integer.valueOf(map.get("email_server_port")));
        // mailAccount.setFrom(map.get("email_send_name"));

        // MailUtil.send(mailAccount, "yangzx1282@qq.com", "test", "test", false, null);
    }
}