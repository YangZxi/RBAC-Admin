/**
 * Copyright: 2019-2020
 * FileName: MailController
 * Author:   Young
 * Date:     2020/6/29 8:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.controller;

import cn.hutool.core.util.StrUtil;
import cn.xiaosm.yadmin.basic.annotation.LogRecord;
import cn.xiaosm.yadmin.basic.util.mail.MailConfig;
import cn.xiaosm.yadmin.basic.entity.Prop;
import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.vo.MailVO;
import cn.xiaosm.yadmin.basic.service.PropService;
import cn.xiaosm.yadmin.basic.util.mail.MailUtils;
import cn.xiaosm.yadmin.basic.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import java.io.*;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/29
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    PropService propService;

    @PostMapping
    @LogRecord("邮箱配置修改")
    @PreAuthorize("hasAuthority('email:modify') or hasRole('admin')")
    public ResponseBody modifyTask(@RequestBody List<Prop> props) {
        boolean b = true;
        for (Prop prop : props) {
            b = propService.modifyEntity(prop);
        }
        // 更新邮箱配置
        MailUtils.updateMail();
        return b ? ResponseUtils.buildSuccess("保存成功")
            : ResponseUtils.buildError("保存失败");
    }

    @PostMapping("/send")
    @LogRecord("邮件发送")
    @PreAuthorize("hasAuthority('email:send') or hasRole('admin')")
    public ResponseBody sendMail(MailVO mailVO) throws IOException {
        MailUtils.sendMail(mailVO);
        return ResponseUtils.buildSuccess("邮件发送成功，请查看收件箱");
    }

}