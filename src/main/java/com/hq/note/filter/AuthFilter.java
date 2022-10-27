package com.hq.note.filter;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hq.note.constant.RedisConstant;
import com.hq.note.enums.ResultCode;
import com.hq.note.utils.PackageHttpServletRequestWrapper;
import com.hq.note.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Value("${token.key}")
    private String key;

    private static final String LOGIN_URL = "/note/user/login";
    private static final String[] SWAGGER_EXCLUDES = new String[]{"/note/swagger-ui/**", "/note/swagger-resources/**",
            "/note/v3/api-docs", "/note/doc.html", "/note/webjars/**", "/note/v2/api-docs", "/note/favicon.ico"};

    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 获取请求接口
        String uri = request.getRequestURI();

        // 登录接口不拦截
        if (LOGIN_URL.equals(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // swagger不拦截
        for (String excludeUri : SWAGGER_EXCLUDES) {
            if (MATCHER.match(excludeUri, uri)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

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

        // 获取请求头token
        String requestToken = request.getHeader("Authorization");

        // 请求头token或者请求参数userId为空则没权限
        if (StringUtils.isBlank(requestToken) || requestUserId == null) {
            returnUnauthorized(servletResponse);
            return;
        }

        // 获取token附带信息
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(requestToken).getBody();
        } catch (Exception e) {
            returnUnauthorized(servletResponse);
            return;
        }

        // 获取用户id
        Object userId = claims.get("userId");
        if (Long.parseLong(userId.toString()) != requestUserId) {
            returnUnauthorized(servletResponse);
            return;
        }

        // 校验token是否过期
        String redisToken = stringRedisTemplate.opsForValue().get(RedisConstant.LOGIN_TOKEN + userId);
        if (StringUtils.isNotBlank(redisToken) && redisToken.equals(requestToken)) {
            filterChain.doFilter(request, servletResponse);
            return;
        }

        // 返回无权访问
        returnUnauthorized(servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 返回无权访问
     *
     * @author HQ
     **/
    private void returnUnauthorized(ServletResponse servletResponse) {
        servletResponse.setCharacterEncoding("UTF-8");
        ResultVO<String> resultVO = new ResultVO<>(ResultCode.UNAUTHORIZED);
        ServletUtil.write((HttpServletResponse) servletResponse, JSON.toJSONString(resultVO), "application/json");
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
