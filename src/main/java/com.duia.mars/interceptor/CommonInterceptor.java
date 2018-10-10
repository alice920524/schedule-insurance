package com.duia.mars.interceptor;

import com.duia.mars.common.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.duia.mars.common.constant.Constant.VERIFY_FAIL_PAGE;

/**
 * Created by Administrator on 2017/9/20.
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是验证失败页面的请求直接放行，避免死循环
        if (StringUtils.equals(request.getRequestURI(), VERIFY_FAIL_PAGE)) {
            return true;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if ("get".equals(request.getMethod().toLowerCase()) && modelAndView != null) {
            String viewName = modelAndView.getViewName();
            if (!StringUtils.contains(viewName, "redirect")) {
                //根据浏览器类型返回对应的视图
                if (WebUtil.isWap(request)) {
                    viewName = "wap/" + viewName;
                } else {
                    viewName = "web/" + viewName;
                }
                modelAndView.setViewName(viewName);
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
