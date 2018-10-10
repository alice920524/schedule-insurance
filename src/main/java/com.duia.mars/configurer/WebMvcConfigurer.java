package com.duia.mars.configurer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.duia.mars.common.util.WebUtil;
import com.duia.mars.core.Result;
import com.duia.mars.core.ResultCode;
import com.duia.mars.core.ServiceException;
import com.duia.mars.interceptor.CommonInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

    @Value("${config.cross-origin}")
    private String CrossOrigin;

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                ModelAndView mv = new ModelAndView();
                Result result = new Result();
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    if (null != handlerMethod.getBean().getClass().getAnnotation(RestController.class)
                            || null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {//JSON视图
                        if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                            logger.info(e.getMessage());
                        } else if (e instanceof ServletException) {
                            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                        } else {
                            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                            String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                    request.getRequestURI(),
                                    handlerMethod.getBean().getClass().getName(),
                                    handlerMethod.getMethod().getName(),
                                    e.getMessage());
                            logger.error(message, e);
                        }
                        responseResult(response, result);
                    } else {//普通页面
                        String viewName500 = WebUtil.isWap(request) ? "wap/error/500" : "web/error/500";
                        mv.setViewName(viewName500);
                        logger.error(e.getMessage(), e);
                    }
                } else {
                    String viewName500 = WebUtil.isWap(request) ? "wap/error/500" : "web/error/500";
                    mv.setViewName(viewName500);
                    logger.error(e.getMessage(), e);
                }
                return mv;
            }
        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(CrossOrigin);
    }


    //添加拦截器
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor());
        super.addInterceptors(registry);
    }


    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrorPageRegistrar() {
            @Override
            public void registerErrorPages(ErrorPageRegistry registry) {
                registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            }
        };
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
