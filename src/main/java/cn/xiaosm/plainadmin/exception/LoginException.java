/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: LoginException
 * Author:   Young
 * Date:     2020/6/15 20:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
}