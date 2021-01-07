package com.qibao.common.logger;

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
@WebFilter(urlPatterns = "/*", filterName = "servletFilter")
public class ServletFilter implements Filter {

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
