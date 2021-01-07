package com.qibao.common.logger;

import com.sun.jndi.toolkit.url.UrlUtil;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 日志拦截器
 *
 * @author qibao
 * @version v0.1 2021/1/7
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    /**
     * logback 日志记得添加这个变量
     */
    private static final String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        putTraceId();
        String requestParam = null;
        try {
            requestParam = getRequestParam(request).toString();
        } catch (Exception e) {
            logger.info("传入参数解析出错", e);
        }
        logger.info(requestParam);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        MDC.remove(TRACE_ID);
    }

    /**
     * 设置traceId
     */
    private void putTraceId() {
        if (StringUtils.isEmpty(MDC.get(TRACE_ID))) {
            String trace_id = UUID.randomUUID().toString().replaceAll("-", "");
            MDC.put(TRACE_ID, trace_id);
        }
    }


    private Map<String, Object> getRequestParam(HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("servletPath", request.getServletPath());
        String contentType = request.getContentType();
        map.put("contentType", request.getContentType());
        String queryString = request.getQueryString();
        queryString = (queryString == null ? queryString : UrlUtil.decode(queryString, "UTF-8"));
        map.put("queryString", queryString);

        ServletInputStream inputStream = request.getInputStream();
        String type = null;
        if (!StringUtils.isEmpty(contentType)) {
            type = contentType.split(";")[0];
        }
        StringBuffer bufferParam = new StringBuffer();
        if ("application/json".equals(type)) {
            BodyReaderServletRequestWapper wapper = new BodyReaderServletRequestWapper(request);
            BufferedReader reader = wapper.getReader();
            String line = reader.readLine();
            while (line != null) {
                bufferParam.append(line);
                line = reader.readLine();
            }
        } else {
            Map<String, String[]> requestParameterMap = request.getParameterMap();
            bufferParam.append(requestParameterMap);
        }

        map.put("params", bufferParam);

        return map;
    }

}
