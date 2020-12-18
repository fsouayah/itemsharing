package com.itemsharing.zuulserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));

        logger.debug("UserContextFilter CorrelationId {}", UserContextHolder.getContext().getCorrelationId());
        logger.debug("UserContextFilter AuthToken {}", UserContextHolder.getContext().getAuthToken());
        logger.debug("UserContextFilter UserId {}", UserContextHolder.getContext().getUserId());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
