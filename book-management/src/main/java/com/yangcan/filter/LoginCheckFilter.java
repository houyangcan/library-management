package com.yangcan.filter;

import com.alibaba.fastjson.JSON;
import com.yangcan.common.api.ApiCode;
import com.yangcan.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private final static String SESSION_KEY = "user";

    public static final List<String> NO_NEED_INTERCEPT_URL = Arrays.asList("/hyc/test/user/**", "/hyc/test/swagger-ui/**", "/hyc/test/swagger-resources/**", "/hyc/test/v3/**");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 判断路径是否需要拦截
        String requestURI = request.getRequestURI();
        if (ifNeedIntercept(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 判断用户是否登录
        if (request.getSession().getAttribute(SESSION_KEY) != null) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(ApiResult.fail("user not login")));
    }

    public Boolean ifNeedIntercept(String requestURI) {
        for (String url : NO_NEED_INTERCEPT_URL) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }

        return false;
    }
}
