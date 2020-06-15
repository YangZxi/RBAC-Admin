/**
 * Copyright: 2019-2020 WWW.XIAOSM.CN
 * FileName:    ValidtedExceptionHandler
 * Author:      Young
 * Date:        2020/4/7 16:25
 * Description:
 * History:
 */
package cn.xiaosm.plainadmin.config;

import cn.xiaosm.plainadmin.utils.Response;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
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
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity catchValidException(MethodArgumentNotValidException ex) {
        return Response.buildError(ex.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

}