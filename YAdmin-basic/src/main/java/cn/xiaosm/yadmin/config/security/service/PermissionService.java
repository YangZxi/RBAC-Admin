/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: PermissionServiceImpl
 * Author:   Young
 * Date:     2020/6/16 21:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.config.security.service;

import cn.xiaosm.yadmin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
@Service(value = "ok")
public class PermissionService {

    @Autowired
    private TokenService tokenService;

    /**
     * 判断是否修改自己的个人信息
     * @param id
     * @return
     */
    public boolean isMe(String id) {
        if (SecurityUtils.getUserDetails().getId() == Integer.valueOf(id)) {
            return true;
        }
        return false;
    }

    public boolean check(String ...permission) {
        for (String s : permission) {

        }
        return false;
    }

}