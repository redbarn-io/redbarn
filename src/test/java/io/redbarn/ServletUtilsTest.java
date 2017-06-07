package io.redbarn;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Tests methods in the ServletUtils class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class ServletUtilsTest {

    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext context;

    @BeforeTest(groups = "Fast")
    public void setupTest() {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        context = mock(ServletContext.class);
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(context);
    }

    @Test(groups = "Fast", expectedExceptions = IllegalArgumentException.class)
    public void getWebVariable_RequestIsNull_Throws() {
        ServletUtils.getWebVariable("foo", null);
    }

    @Test(groups = "Fast", expectedExceptions = IllegalArgumentException.class)
    public void getWebVariable_NameIsNullOrEmpty_Throws() {
        ServletUtils.getWebVariable(null, request);
    }

    @Test(groups = "Fast")
    public void getWebVariable_VariableInRequestInRequestScope_ReturnsNonNull() {
        when(request.getAttribute("foo")).thenReturn("bar");
        Object actual = ServletUtils.getWebVariable("foo", request);
        assertEquals(actual, "bar");
    }

    @Test(groups = "Fast")
    public void getWebVariable_VariableInRequestParameters_ReturnsNonNull() {
        when(request.getParameter("foo")).thenReturn("bar");
        Object actual = ServletUtils.getWebVariable("foo", request);
        assertEquals(actual, "bar");
    }

    @Test(groups = "Fast")
    public void getWebVariable_VariableInSessionScope_ReturnsNonNull() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("foo")).thenReturn("bar");
        Object actual = ServletUtils.getWebVariable("foo", request);
        assertEquals(actual, "bar");
    }

    @Test(groups = "Fast")
    public void getWebVariable_VariableInApplicationScope_ReturnsNonNull() {
        when(context.getAttribute("foo")).thenReturn("bar");
        Object actual = ServletUtils.getWebVariable("foo", request);
        assertEquals(actual, "bar");
    }

    @Test(groups = "Fast", expectedExceptions = IllegalArgumentException.class)
    public void getWebVariables_RequestIsNull_Throws() {
        String[] names = new String[1];
        ServletUtils.getWebVariables(names, null);
    }

    @Test(groups = "Fast", expectedExceptions = IllegalArgumentException.class)
    public void getWebVariables_NamesIsNull_Throws() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        ServletUtils.getWebVariables(null, request);
    }

    @Test(groups = "Fast")
    public void getWebVariables_NamesAndRequestAreValid_ReturnsNonNull() {
        when(request.getParameter("foo")).thenReturn("bar");
        when(request.getParameter("foos")).thenReturn("bars");
        String[] names = new String[] { "foo", "foos" };
        Object[] vars = ServletUtils.getWebVariables(names, request);
        assertNotNull(vars);
    }

    @Test(groups = "Fast")
    public void getWebVariables_NamesAreInvalidAndRequestIsValid_ReturnsNonNull() {
        String[] names = new String[] { "baz", "bazels" };
        Object[] vars = ServletUtils.getWebVariables(names, request);
        assertNotNull(vars);
    }

    @Test(groups = "Fast")
    public void getWebVariables_ParameterIsRequest_ReturnsServletRequest() {
        String[] names = new String[] { "baz", "request" };
        Object[] vars = ServletUtils.getWebVariables(names, request);
        assertEquals(vars[1], request);
    }
}
