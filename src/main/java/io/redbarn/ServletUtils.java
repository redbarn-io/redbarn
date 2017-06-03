package io.redbarn;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles low level details involved with the Java Servlets API.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class ServletUtils {

    /**
     * Gets a web oriented variable by first checking to see if the variable is
     * available in the the request attributes, the request parameters, the
     * session, or finally the context in that order.  Once a variable is
     * found, further processing is discontinued, meaning that the
     * <em>lowest</em> HttpScope wins.
     *
     * @param request The Http Servlet Request from which to search.
     * @param name The name of the variable for which to search.
     * @return The variable or null if not found.
     */
    public static Object getWebVariable(String name, HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("The 'request' argument cannot be null");
        }
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("The 'name' argument cannot be null or empty.");
        }

        Object variable = request.getAttribute(name);
        if (variable == null) {
            variable = request.getParameter(name);
        }
        if (variable == null) {
            variable = request.getSession().getAttribute(name);
        }
        if (variable == null) {
            variable = request.getServletContext().getAttribute(name);
        }
        return variable;
    }
}
