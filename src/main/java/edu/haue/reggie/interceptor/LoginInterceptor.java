package edu.haue.reggie.interceptor;

import com.alibaba.fastjson.JSON;
import edu.haue.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("拦截的请求路径是：{}", requestURI);
        //检查是否登录
        if (request.getSession().getAttribute("employee") != null) {
            return true;
        }
        //未登录,通过输出流的方式向客户端响应数据（交给前端js拦截器处理以及跳转）
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return false;
    }
}
