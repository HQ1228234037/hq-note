package com.hq.note.filter;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hq.note.entity.RequestLogsEntity;
import com.hq.note.service.RequestLogsService;
import com.hq.note.utils.PackageHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 鉴权过滤器
 *
 * @author HQ
 **/
@Slf4j
@Component
@WebFilter(filterName = "logsFilter", urlPatterns = "/*")
public class LogsFilter implements Filter {

    @Resource
    private RequestLogsService requestLogsService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = null;
        try {
            request = (HttpServletRequest) servletRequest;

            // 获取请求接口
            String uri = request.getRequestURI();

            // 获取请求参数
            JSONObject requestParams = new JSONObject();
            Map<String, String> paramMap = ServletUtil.getParamMap(request);
            if (MapUtils.isNotEmpty(paramMap)) {
                requestParams.putAll(paramMap);
            } else {
                request = new PackageHttpServletRequestWrapper((HttpServletRequest) servletRequest);
                requestParams = getRequestJson(request);
            }

            // 获取请求参数 userId
            Long requestUserId = requestParams.getLong("userId");

            // 保存日志
            RequestLogsEntity requestLogsEntity = new RequestLogsEntity();
            requestLogsEntity.setUserId(requestUserId);
            requestLogsEntity.setUri(uri);
            requestLogsEntity.setRequestData(JSON.toJSONString(requestParams));
            requestLogsEntity.setToken(request.getHeader("Authorization"));
            requestLogsEntity.setIp(ServletUtil.getClientIP(request));
            requestLogsService.save(requestLogsEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 获取请求参数json对象
     *
     * @param request 请求对象
     * @return 请求参数
     * @author HQ
     **/
    private JSONObject getRequestJson(HttpServletRequest request) {
        String jsonStr = "";
        ServletInputStream inputStream = null;
        try {
            int contentLength = request.getContentLength();
            if (contentLength >= 0) {
                byte[] buffer = new byte[contentLength];
                inputStream = request.getInputStream();
                for (int i = 0; i < contentLength; ) {
                    int len = inputStream.read(buffer, i, contentLength);
                    if (len == -1) {
                        break;
                    }
                    i += len;
                }
                jsonStr = new String(buffer, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        if (StringUtils.isNotBlank(jsonStr)) {
            return JSONObject.parseObject(jsonStr);
        }
        return new JSONObject();
    }

}
