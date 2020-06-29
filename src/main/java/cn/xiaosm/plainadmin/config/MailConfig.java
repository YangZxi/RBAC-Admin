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
package cn.xiaosm.plainadmin.config;

import cn.hutool.extra.mail.MailAccount;
import cn.xiaosm.plainadmin.entity.Prop;
import cn.xiaosm.plainadmin.service.PropService;
import cn.xiaosm.plainadmin.utils.MailUtils;
import cn.xiaosm.plainadmin.utils.SpringContextUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Bean
    public MailAccount createMailAccount() {
        MailAccount account = new MailAccount();
        return account;
    }

    @Bean
    public JavaMailSenderImpl createJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        return sender;
    }

    public void updateMail() {
        List<Prop> propList = propService.list(new QueryWrapper<Prop>().eq("type", "email"));
        Map<String, String> map = new HashMap<>();
        for (Prop prop : propList) {
            map.put(prop.getPropKey(), prop.getPropValue());
        }
        // Springboot
        JavaMailSenderImpl sender = (JavaMailSenderImpl) SpringContextUtils.getBean(JavaMailSender.class);
        sender.setUsername(map.get("email_username"));
        sender.setPassword(map.get("email_password"));
        sender.setHost(map.get("email_server_host"));
        sender.setPort(Integer.valueOf(map.get("email_server_port")));
        // sender.setProtocol("");
        sender.setDefaultEncoding("UTF-8");
        MailUtils.setFromAddress(sender.getUsername());
        MailUtils.setFromName(map.get("email_send_name"));

        // Hutool
        MailAccount mailAccount = SpringContextUtils.getBean(MailAccount.class);
        mailAccount.setUser(map.get("email_username"));
        mailAccount.setPass(map.get("email_password"));
        mailAccount.setHost(map.get("email_server_host"));
        mailAccount.setPort(Integer.valueOf(map.get("email_server_port")));
        mailAccount.setFrom(map.get("email_send_name"));

        // MailUtil.send(mailAccount, "yangzx1282@qq.com", "test", "test", false, null);
    }
}