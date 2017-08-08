package com.hsd.framework.filter;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebFilter(filterName = "crossFilter", urlPatterns = {"/api/*","/boss/*"})
@Order(-99999999)
public class CrossFilter implements Filter {
    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public CrossFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("CrossFilter:Initializing filter");
            }
        }

    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CrossFilter()");
        }
        StringBuffer sb = new StringBuffer("CrossFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (debug) {
            log("CrossFilter:doFilter()");
        }

        if (response instanceof HttpServletResponse) {
            HttpServletRequest alteredRequest = ((HttpServletRequest) request);
            HttpServletResponse alteredResponse = ((HttpServletResponse) response);
            addHeadersFor200Response(alteredRequest,alteredResponse);
        }
        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }


    }

    @Override
    public void destroy() {

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("CrossFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("CrossFilter:DoAfterProcessing");
        }
    }

    private void addHeadersFor200Response(HttpServletRequest request,HttpServletResponse response) {
        String origin=request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin==null?"*":origin);
//        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, HEAD");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Authorization,Cache-Control,Content-Language,Content-Type,Expires,Last-Modified,Pragma,sid");
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许cookies跨域
        response.setHeader("XDomainRequestAllowed","1");
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }


}