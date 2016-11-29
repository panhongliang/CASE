package com.phl.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by panhongliang on 15/8/16.
 */
public class StartUpSevlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"springcontext.xml"});
        BeanFactory factory=context;

    }
}
