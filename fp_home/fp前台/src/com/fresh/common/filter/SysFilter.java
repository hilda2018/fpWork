package com.fresh.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fresh.utils.SessionUtil;
import com.fresh.utils.SysConfigUtils;

/**
 * 系统过滤器(后续需要修改)
 * 
 * @author Chengql
 * @date 2013-11-25
 * @version 1.0
 */
public class SysFilter implements Filter {

    private static Set<String> NEED_CHECK_URLS = new HashSet<String>();
    
    @Override
	public void destroy() {

	}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            String url = req.getRequestURL().toString();
            String baseURL = url.substring(url.lastIndexOf("/") + 1);
            
            //暂时放开
            if (true) {
                chain.doFilter(req, resp);
                return;
            }

            if (needCheck(baseURL)) {
                if (SessionUtil.getMember(req) == null) {
                    // 判断是否登录
                    if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) { // 如果是Ajax请求
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                        // session失效
                        response.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = null;
                        try {
                            out = response.getWriter();
                            out
                                    .println("{\"message\":\"\u5c1a\u672a\u767b\u5f55\uff0c\u7cfb\u7edf\u5373\u5c06\u8df3\u8f6c\u5230\u767b\u5f55\u9875\u9762\",\"data\":{\"redirectUrl\":\""
                                            + SysConfigUtils.getConfigValue(req.getSession().getServletContext(),
                                                    "login") + "\"},\"success\":false}");
                        } catch (IOException e) {

                        } finally {
                            if (out != null) {
                                out.close();
                            }
                        }
                        return;
                    } else { // 如果是非Ajax请求
                        resp.sendRedirect(SysConfigUtils.getConfigValue(req.getSession().getServletContext(), "login"));
                        return;
                    }
                } else {
                    chain.doFilter(req, resp);
                }
            } else {
                chain.doFilter(req, resp);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
        String needToCheckUrl = filterconfig.getInitParameter("needTocheckUrl");
        for (String url : needToCheckUrl.split(",")) {
            SysFilter.NEED_CHECK_URLS.add(url.trim());
        }
    }

    /*
     * uri地址是否需要进行过滤
     * 
     * @param uri
     * 
     * @return
     */
    private boolean needCheck(String uri) {
        boolean needCheck = SysFilter.NEED_CHECK_URLS.contains(uri);
        if (!needCheck) {
            for (String needCheckUrlRegex : NEED_CHECK_URLS) {
                Pattern pattern = Pattern.compile(needCheckUrlRegex);
                Matcher matcher = pattern.matcher(uri);
                needCheck = matcher.find();

                if (needCheck) {
                    break;
                }
            }
        }

        return needCheck;
    }
}