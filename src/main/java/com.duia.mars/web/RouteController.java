package com.duia.mars.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by 李恒名 on 2017/6/5.
 * <p>
 * 路由控制器，所有页面的路由写在该类下，该控制器只允许返回View，且HttpRequest方法必须为Get，返回JSON的接口写在其他RestController里面。
 */
@Controller
public class RouteController {

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error500() {
        return "error/500";
    }

    @GetMapping("/")
    public void home(Model model,HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @RequestMapping("/test")
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

}
