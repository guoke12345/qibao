package com.qibao.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * TODO
 *
 * @author qibao
 * @version v0.1 2021/1/7
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "servletFilter")
public class ServletFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ServletFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequestWrapper wrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            wrapper = new BodyReaderServletRequestWapper((HttpServletRequest) servletRequest);
        }
        if (wrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(wrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
