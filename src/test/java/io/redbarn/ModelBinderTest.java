package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * Tests the scipts/html-processor.js resource
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class ModelBinderTest {

    private ScriptEngine scriptEngine;
    private String scriptTemplate;

    @BeforeClass
    public void setupClass() throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        scriptEngine = manager.getScriptEngine();
    }

    public void setupTest() throws IOException {
        scriptTemplate = ResourceUtils.getResourceString("scripts/html-processor.js");
    }

    @Test(groups = "Slow")
    public void html_MarkupContainsLineBreaks_ConvertsMarkupCorrectly()
            throws IOException, ScriptException, NoSuchMethodException {

        // Merges the model binding script from the template and loads it into
        // the script engine.
        String processFunction = "function process(foo, bar, baz) { $('.change').text(baz); }";
        scriptTemplate = scriptTemplate.replace("'{ProcessFunction}';", processFunction);
        ScriptObjectMirror redbarn = (ScriptObjectMirror) scriptEngine.eval(scriptTemplate);

        // Registers the markup to be transformed.
        String markup = "<html>\n" +
                        "  <body>\n" +
                        "    <div class=\"change\">foo</div>\n" +
                        "  </body>\n" +
                        "</html>";
        redbarn.callMember("markup", markup);

        // Stores the model binder in the script engine for later use.
        String key = "foo";
        redbarn.callMember("save", key);

        // Gets the results of the model binder.
        Object[] args = new Object[3];
        args[0] = "foo";
        args[1] = "bar";
        args[2] = "baz";
        String actual = (String) redbarn.callMember("html", args);
        String expected = "<html>\n" +
                          "  <body>\n" +
                          "    <div class=\"change\">baz</div>\n" +
                          "  </body>\n" +
                          "</html>";
        Assert.assertEquals(actual, expected);
    }

}
