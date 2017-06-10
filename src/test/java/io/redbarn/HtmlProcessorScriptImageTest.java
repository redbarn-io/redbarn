package io.redbarn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


/**
 * Tests the HtmlProcessorScriptImage class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class HtmlProcessorScriptImageTest {

    private ScriptEngine scriptEngine;

    @BeforeClass(groups = "Slow")
    public void classSetup() throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        scriptEngine = manager.getScriptEngine();
    }

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void constructor_ScriptEngineIsNull_Throws()
            throws IOException, ScriptException {
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                null,
                "<html></html>",
                "foo",
                "function process() { // do nothing }"
        );
    }

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void constructor_KeyIsNull_Throws()
            throws IOException, ScriptException {
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                "<html></html>",
                null,
                "function process() { // do nothing }"
        );
    }

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void constructor_ProcessFunctionIsNull_Throws()
            throws IOException, ScriptException {
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                "<html></html>",
                "foo",
                null
        );
    }

    @Test(groups = "Slow")
    public void getParameters_ProcessorFunctionHasNoParameters_ReturnsNonNull()
            throws IOException, ScriptException {
        String processFunction = "function process() { alert('noop'); }";
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                "<html></html>",
                "foo",
                processFunction
        );
        String[] params = processor.getParameters();
        assertNotNull(params);
    }

    @Test(groups = "Slow")
    public void getParameters_ProcessorFunctionHasParameters_ReturnsAllParameters()
            throws IOException, ScriptException {
        String processFunction = "function process(foo, bar, baz) { alert('noop'); }";
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                "<html></html>",
                "foo",
                processFunction
        );
        String[] params = processor.getParameters();
        assertEquals(params.length, 3);
    }

    @Test(groups = "Slow")
    public void getBodyMarkup_MarkupHasLineBreaks_Succeeds() throws IOException, ScriptException {
        String markup =
                "<html> \n" +
                "  <body> \n" +
                "  <div class=\"change\">foo</div>\n" +
                "  </body> \n" +
                "</html>";
        String processFunction =
                "function process(foo, request) { \n" +
                "  $('.change').text(foo); \n" +
                "}";
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                markup,
                "foo",
                processFunction
        );

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("foo")).thenReturn("bar");
        String expected = "<div class=\"change\">bar</div>";
        String actual = processor.getBodyMarkup(request).trim();
        assertEquals(actual, expected);
    }

    @Test(groups = "Slow")
    public void getHtmlMarkup_MarkupHasLineBreaks_Succeeds() throws IOException, ScriptException {
        String markup =
                "<html> \n" +
                "  <body> \n" +
                "  <div class=\"change\">foo</div>\n" +
                "  </body> \n" +
                "</html>";
        String processFunction =
                "function process(foo, request) { \n" +
                "  $('.change').text(foo); \n" +
                "}";
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                markup,
                "foo",
                processFunction
        );

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("foo")).thenReturn("bar");
        String expected =
                "<html> \n" +
                "  <body> \n" +
                "  <div class=\"change\">bar</div>\n" +
                "  </body> \n" +
                "</html>";
        String actual = processor.getHtmlMarkup(request);
        assertEquals(actual, expected);
    }

    @Test(groups = "Slow")
    public void getHtmlMarkup_ProcessFunctionCallsRepeat_ReturnsExpected() throws IOException, ScriptException {
        String markup =
                "<html> \n" +
                "  <body> \n" +
                "    <ul> \n" +
                "      <li>foo</li> \n" +
                "    </ul> \n" +
                "  </body> \n" +
                "</html>";
        String processFunction =
                "function process(fruit, request) { \n" +
                "  $('ul > li').repeat(fruit, function(type, li) { \n" +
                "    li.text(type); \n" +
                "  }); \n" +
                "}";
        HtmlProcessorScriptImage processor = new HtmlProcessorScriptImage(
                scriptEngine,
                markup,
                "foo",
                processFunction
        );

        String[] fruit = new String[] { "apples", "pears", "plums" };

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("fruit")).thenReturn(fruit);
        String expected =
                "<html> \n" +
                "  <body> \n" +
                "    <ul><li>apples</li><li>pears</li><li>plums</li></ul> \n" +
                "  </body> \n" +
                "</html>";
        String actual = processor.getHtmlMarkup(request);
        assertEquals(actual, expected);
    }
}
