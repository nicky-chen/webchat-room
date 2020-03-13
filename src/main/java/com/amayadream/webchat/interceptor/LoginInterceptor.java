package com.amayadream.webchat.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private List<String> IGNORE_URI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        //获取URI后缀
        String requestUri = request.getServletPath();

        if (requestUri.equalsIgnoreCase("/"))
            return true;

        //过滤不需要拦截的地址
        for (String uri : IGNORE_URI) {
            if (requestUri.startsWith(uri)) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("login_status") != null) {
            return true;
        } else {
            response.sendRedirect("/user/login?timeout=true");
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    public List<String> getIGNORE_URI() {
        return IGNORE_URI;
    }

    public void setIGNORE_URI(List<String> IGNORE_URI) {
        this.IGNORE_URI = IGNORE_URI;
    }
}
