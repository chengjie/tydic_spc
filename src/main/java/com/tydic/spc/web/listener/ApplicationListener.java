package com.tydic.spc.web.listener;


import com.tydic.spc.web.ApplicationServletContext;
import com.tydic.spc.web.PageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 应用侦听器
 *
 * @author yiding.he
 */
public class ApplicationListener implements
        HttpSessionListener, ServletContextListener, ServletRequestListener {

    private static final Logger log = LoggerFactory.getLogger(ApplicationListener.class);

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //session创建时触发
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //session销毁时触发
    }

    /////////////////////////////////////////

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            initApplication(servletContextEvent);
        } catch (Exception e) {
            log.error("项目初始化失败", e);
        }
    }

    /**
     * 应用程序初始化
     *
     * @param event ServletContextEvent
     */
    private void initApplication(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        ApplicationServletContext.setContext(context);
        initCache();
        initTask();
    }

    /**
     * 初始化加载缓存
     */
    private void initCache() {
        
    }

    /**
     * 初始化启动定时任务
     */
    private void initTask() {
        
    }

    /**
     * 系统停止时所做处理
     *
     * @param servletContextEvent servletContextEvent
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //应用关闭时触发
    }

    /////////////////////////////////////////

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        PageContext.setRequest(request);
    }
}
