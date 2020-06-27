package cn.xiaosm.plainadmin.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import cn.xiaosm.plainadmin.annotation.LogRecord;
import cn.xiaosm.plainadmin.config.security.service.TokenService;
import cn.xiaosm.plainadmin.entity.Log;
import cn.xiaosm.plainadmin.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

@Aspect
@Component
public class ExceptionAspect {

    @Autowired
    TokenService tokenService;
    @Autowired
    LogService logService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("execution(public * cn.xiaosm.plainadmin.config.GlobalExceptionHandler.catchException(..))")
    public void logPointCut() { }

    //切面 后置通知
    @AfterReturning("logPointCut()")
    public void after(JoinPoint joinPoint) {
        Log log = new Log();

        log.setContent(this.getStackTrace((Exception) joinPoint.getArgs()[0]));
        log.setTitle("运行异常");
        log.setType("ERROR");

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.setIp(ServletUtil.getClientIP(request));
        logService.addEntity(log);
    }

    public String getStackTrace(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        e.printStackTrace(ps);
        try {
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ps.close();
        return out.toString();
    }

}
