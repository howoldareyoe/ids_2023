package com.example.idsdemo.aop;

import com.example.idsdemo.annotation.RolePermissionVerificationAnno;
import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.dto.SessionWebUserDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName RolePermissionVerificationAspect
 * @Description TODO
 * @Author YU
 * @Date 2024/4/2 15:39
 * @Version 1.0
 **/


@Component
@Aspect
public class RolePermissionVerificationAspect {

    @Pointcut("@annotation(com.example.idsdemo.annotation.RolePermissionVerificationAnno)")
    public void rolePermissionCheckPointCut() {
    }

    @Around("rolePermissionCheckPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 获取正在访问的方法
        Method executionMethod = methodSignature.getMethod();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        Object result;
        String message = "您无权进行此操作";
        if (executionMethod.getReturnType().getTypeName().contains(Result.class.getSimpleName())) {
            result = Result.error( message);
        } else {
            result = message;
        }
        if(sra == null){
            return result;
        }
        HttpServletRequest request = sra.getRequest();
        Integer userId = null;
        Integer roleId = null;
        SessionWebUserDto userData = (SessionWebUserDto) request.getSession().getAttribute("session_key");
        if (userData != null) {
//            userId = userData.get();
            roleId = userData.getRoleId();
        }
        RolePermissionVerificationAnno annotation = executionMethod.getAnnotation(RolePermissionVerificationAnno.class);
        //获取注解中的types字段
        long[] roleIdList = annotation.roleIdList();
//        long[] userIdList = annotation.userIdList();
        //该用户是否有访问权限
        boolean flag = false;
        //是否需要进行校验 当用户列表和角色列表同时为空时不进行校验
        boolean checkFlag = false;
        if (roleIdList.length > 0) {
            //需要进行用户角色权限校验
            checkFlag = true;
            if (roleId != null) {
                for (long role : roleIdList) {
                    if (role == roleId) {
                        flag = true;
                        break;
                    }
                }
            }
        }
//        if (userIdList.length > 0) {
//            checkFlag = true;
//            if (userId != null) {
//                for (long uid : userIdList) {
//                    if (uid == userId) {
//                        flag = true;
//                        break;
//                    }
//                }
//            }
//        }
        if (checkFlag) {
            //需要进行校验
            if (flag) {
                //校验通过
                result = pjp.proceed();
            }
        }else {
            //不需要进行校验
            result = pjp.proceed();
        }
        return result;
    }

}
