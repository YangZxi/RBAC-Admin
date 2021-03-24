/**
 * Copyright: 2019-2020 WWW.XIAOSM.CN
 * FileName:    ValidtedExceptionHandler
 * Author:      Young
 * Date:        2020/4/7 16:25
 * Description:
 * History:
 */
package cn.xiaosm.yadmin.basic.config;

import cn.xiaosm.yadmin.basic.entity.enums.ResponseStatus;
import cn.xiaosm.yadmin.basic.exception.CanShowException;
import cn.xiaosm.yadmin.basic.util.ResponseUtils;
import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/4/7
 * @version 1.0.0
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseBody catchException(Throwable e) {
        log.error(e.getMessage());
        return ResponseUtils.buildError("请求出错，详情请查看后台日志");
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBody catchValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return ResponseUtils.buildError(e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseBody catchLoginException(AuthenticationException e) {
        log.error(e.getMessage());
        return ResponseUtils.buildError(e.getMessage(), null);
    }

    /**
     * 没有访问权限。使用 @PreAuthorize 校验权限不通过时，就会抛出 AccessDeniedException 异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseBody catchAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return ResponseUtils.build(ResponseStatus.AUTHORITIES_DENIED, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseBody catchUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseUtils.buildError(e.getMessage());
    }

    @ExceptionHandler(CanShowException.class)
    public ResponseEntity catchCanShowException(CanShowException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                ResponseUtils.buildError(e.getMessage()), HttpStatus.BAD_REQUEST
        );
    }

}