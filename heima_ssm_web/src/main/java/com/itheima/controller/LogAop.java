package com.itheima.controller;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;  // 系统访问开始时间
    private Class clazz;     // 访问的类
    private Method method;   // 访问的方法

    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();  //当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取具体执行的方法放入method对象
        if(args == null || args.length == 0){
            //无参
            method = clazz.getMethod(methodName); //根据方法名获取方法对象，只能获取无参数的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass(); //获取每一个参数的class存储到数组中
            }
            method = clazz.getMethod(methodName,classArgs);
        }

    }

    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        long time = new Date().getTime() - visitTime.getTime();
        String url = "";
        if (clazz != null && method != null && clazz != SysLog.class) {
            // 1.获取类上的RequestMapping("/product")
            RequestMapping classAnntation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnntation != null) {
                String [] calssValue = classAnntation.value();

                // 2.获取方法上的RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String [] methodValue = methodAnnotation.value();

                    url = calssValue[0] + methodValue[0];

                    // 获取访问的IP
                    String ip = request.getRemoteAddr();

                    // 获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();   // 从上下文中获取当前登录的用户对象
                    User user =(User)context.getAuthentication().getPrincipal();
                    String userName = user.getUsername();

                    // 将日志相关信息封装在SysLog 对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + " [方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(userName);
                    sysLog.setVisitTime(visitTime);

                    System.out.println("sysLog: " + sysLog);

                    // 调用Service完成操作
                    if (clazz.getName() != "com.hz.controller.SysLogController") {
                        try {
                            sysLogService.save(sysLog);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    
}
