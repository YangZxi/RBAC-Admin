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
package cn.xiaosm.yadmin.controller;

import cn.hutool.core.util.StrUtil;
import cn.xiaosm.yadmin.annotation.LogRecord;
import cn.xiaosm.yadmin.config.MailConfig;
import cn.xiaosm.yadmin.entity.Prop;
import cn.xiaosm.yadmin.entity.ResponseBody;
import cn.xiaosm.yadmin.entity.vo.MailVO;
import cn.xiaosm.yadmin.service.PropService;
import cn.xiaosm.yadmin.util.MailUtils;
import cn.xiaosm.yadmin.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    PropService propService;
    @Autowired
    MailConfig mailConfig;

    @PostMapping
    @LogRecord("邮箱配置修改")
    @PreAuthorize("hasAuthority('email:modify') or hasRole('admin')")
    public ResponseBody modifyTask(@RequestBody List<Prop> props) {
        boolean b = true;
        for (Prop prop : props) {
            b = propService.modifyEntity(prop);
        }
        // 更新邮箱配置
        mailConfig.updateMail();
        return b ? ResponseUtils.buildSuccess("保存成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @PostMapping("/send")
    @LogRecord("邮件发送")
    @PreAuthorize("hasAuthority('email:send') or hasRole('admin')")
    public ResponseBody sendMail(MailVO mailVO) throws IOException {
        if (StrUtil.isNotBlank(mailVO.getTemplate())) {
            InputStream in = this.getClass().getResourceAsStream("/template/" + mailVO.getTemplate());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(out);
            byte[] size = new byte[2048];
            int length = 0;
            try {
                while ((length = in.read(size)) != -1) {
                    ps.write(size, 0, length);
                }
                System.out.println(out.toString());
                mailVO.setContent(out.toString());
                mailVO.setIsHtml(true);
            } catch (IOException e) {

            } finally {
                ps.close();
                out.close();
            }
        }
        MailUtils.sendMail(mailVO.getTo(), mailVO.getSubject(), mailVO.getContent(), mailVO.getIsHtml());
        return ResponseUtils.buildSuccess("邮件发送成功，请查看收件箱");
    }

}