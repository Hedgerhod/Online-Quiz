/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filters;

import Entity.User;
import Model.DAOUser;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author hieul
 */
public class AuthorizationFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthorizationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoBeforeProcessing");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            // Check logined-user exist in session ~ for case user in a session + url without any parameter + cookie timeout
            HttpSession session = req.getSession(); // session is existed surely
            User user = (User) session.getAttribute("acc");

            // 1. Get role
            int roleId = 0;
            if (user != null) { // logined
                roleId = user.getRoleId();
            }
            String role = "";
            switch (roleId) {
                case 0:
                    role = "guest";
                    break;
                case 1:
                    role = "student";
                    break;
                case 2:
                    role = "teacher";
                    break;
                case 3:
                    role = "admin";
                    break;
            }
        } finally {

        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoAfterProcessing");
        }

    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("acc"); // Lấy thông tin người dùng từ session
        int roleId;
        if (user != null) {
            roleId = user.getRoleId(); // Nếu người dùng đã đăng nhập, lấy quyền từ thông tin người dùng
        } else {
            roleId = 0; // Nếu người dùng chưa đăng nhập, giả định rằng họ là guest
        }

        String role = "";
        switch (roleId) {
            case 0:
                role = "guest";
                break;
            case 1:
                role = "student";
                break;
            case 2:
                role = "teacher";
                break;
            case 3:
                role = "admin";
                break;
        }

        String servletPath = req.getServletPath(); // Lấy đường dẫn hiện tại

        //get resource name
        int lastIndex = servletPath.lastIndexOf("/");
        String resource = servletPath.substring(lastIndex + 1);

        //    System.out.println(servletPath);
        // Lấy thông tin từ roleMaps.properties        
        ServletContext context = request.getServletContext();
        Properties roleMaps = (Properties) context.getAttribute("ROLE_MAPS");
        //    System.out.println(roleMaps);

        String allowedRoles = roleMaps.getProperty(resource); // Lấy danh sách quyền được phép truy cập trang này
        
        System.out.println(role);
        System.out.println(allowedRoles);

        if (allowedRoles != null) {
            List<String> rolesList = Arrays.asList(allowedRoles.split(","));
            if (rolesList.contains(role)) {
                chain.doFilter(req, res);
                return;// Nếu quyền hiện tại có trong danh sách, cho phép truy cập
            } else {
                res.sendRedirect(req.getContextPath() + "/ErrorURL"); // Nếu không, chuyển hướng đến trang thông báo lỗi
            }
        } else {
            chain.doFilter(req, res); // Nếu không có thông tin về quyền truy cập, cho phép truy cập
        }

//        Throwable problem = null;
//        try {
//            chain.doFilter(request, response);
//        } catch (Throwable t) {
//            // If an exception is thrown somewhere down the filter chain,
//            // we still want to execute our after processing, and then
//            // rethrow the problem after that.
//            problem = t;
//            t.printStackTrace();
//        }
//        doAfterProcessing(request, response);
//
//        // If there was a problem, we want to rethrow it if it is
//        // a known type, otherwise log it.
//        if (problem != null) {
//            if (problem instanceof ServletException) {
//                throw (ServletException) problem;
//            }
//            if (problem instanceof IOException) {
//                throw (IOException) problem;
//            }
//            sendProcessingError(problem, response);
//        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthorizationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthorizationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
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
