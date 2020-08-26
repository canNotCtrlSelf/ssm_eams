package com.cncs.controller;

import com.cncs.domain.SysLog;
import com.cncs.service.ISysLogService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Controller
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Pointcut("execution(* com.cncs.controller.*.*(..)) && !execution(* com.cncs.controller.SysLogController.*(..))")//自定义切入点
    public void pt1(){};

    //获取访问的类，访问的方法，访问的时间
    @Before("pt1()")//记录所有controller访问过程
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//访问时间
        executionClass = jp.getTarget().getClass();//访问的类
        //获取访问的方法
        String methodName = jp.getSignature().getName();//访问的方法名
        Object[] args = jp.getArgs();//访问的方法的参数
        if (args == null || args.length == 0) {//无参数
            executionMethod = executionClass.getMethod(methodName);
        } else {
            //有参数，将args中所有元素遍历，获取对应的class，装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classArgs);//获取有参数的方法
        }
    }

    //获取执行的时间，访问的用户名称，用户ip，访问的url，
    @After("pt1()")
    public void doAfter() {
        //执行的时间
        long executionTime = new Date().getTime() - visitTime.getTime();
        //用户访问的url
        String url = "";
        if (executionClass != LogAop.class) {
            //获取controller上的@RequestMapping("/product")
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                //获取方法上的@RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] classValues = classAnnotation.value();//获取controller类参数数组
                    String[] methodValues = methodAnnotation.value();//获取方法参数数组
                    url = classValues[0] + methodValues[0];
                }
            }
        }
        //用户ip
        String ip = request.getRemoteAddr();
        //用户名称
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = ((User) (securityContext.getAuthentication().getPrincipal())).getUsername();//也可以通过request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")获取
        //封装SysLog
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+executionClass.getName()+"[方法名]"+executionMethod.getName());
        sysLog.setUsername(username);
        sysLog.setUrl(url);
        //存入数据库
        sysLogService.save(sysLog);
    }
}
