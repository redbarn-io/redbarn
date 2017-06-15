package io.redbarn;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    public static Object getWebVariable(
            String name,
            HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("The 'request' argument cannot be null");
        }
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("The 'name' argument cannot be null or empty.");
        }

        Object variable = null;
        if (!name.toLowerCase().equals("request") &&
            !name.equals("$") &&
            !name.equals("_")) {
            variable = request.getAttribute(name);
            if (variable == null) {
                variable = request.getParameter(name);
            }
            if (variable == null) {
                variable = request.getSession().getAttribute(name);
            }
            if (variable == null) {
                variable = request.getServletContext().getAttribute(name);
            }
        }
        return variable;
    }

    /**
     * <p>
     *     Gets a collection of web oriented variables by first checking to see
     *     if each variable is available in the the request attributes, the
     *     request parameters, the session, or finally the context in that
     *     order.  Once a variable is found, further processing is discontinued,
     *     meaning that the <em>lowest</em> HttpScope wins.
     * </p>
     *
     * <p>
     *     In addition to the variables specified in the list of names, the
     *     HttpServletRequest itself will be provided as the <em>last</em>
     *     variable in the list.
     * </p>
     *
     * @param names The name of the variable for which to search.
     * @param request The Http Servlet Request from which to search.
     * @return A non-null list of variables.
     */
    public static Object[] getWebVariables(
            String[] names,
            HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("The 'request' argument cannot be null");
        }
        if (names == null) {
            throw new IllegalArgumentException("The 'name' argument cannot be null or empty.");
        }

        List<Object> variables = new ArrayList<>();
        for(String name : names) {
            if (name.toLowerCase().equals("request")) {
                variables.add(request);
            } else if(name.equals("$") || name.equals("_")) {
                // Do nothing, these will be added by JavaScript
            } else {
                Object variable = getWebVariable(name, request);
                variables.add(variable);
            }
        }
        return variables.toArray();
    }

}
