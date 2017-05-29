package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * Tests the scipts/model-binder.js resource
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class ModelBinderTest {

    private ScriptEngine scriptEngine = null;

    @BeforeClass
    public void classSetup() throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        scriptEngine = manager.getScriptEngine();
    }

    @Test(groups = "Unit")
    public void iife_MarkupContainsNoLineBreaks_LoadsMarkupInCheerio()
            throws IOException, ScriptException {
        String binder = RedbarnScriptEngineManager.getResourceAsString("scripts/model-binder.js");
        String markup = "<html><body><div>foo</div></body></html>";
        String name = "foo";
        String bindFunction = "function bind() { }";
        binder = binder.replace("%markup%", markup);
        binder = binder.replace("%redbarnName%", name);
        binder = binder.replace("'%Replace with model binding functions%';", bindFunction);
        ScriptObjectMirror redbarn = (ScriptObjectMirror) scriptEngine.eval(binder);
        String bound = (String) redbarn.callMember("html");
        Assert.assertEquals(bound, markup);
    }

    @Test(groups = "Unit")
    public void iife_MarkupContainsLineBreaks_LoadsMarkupInCheerio()
            throws IOException, ScriptException {
        String binder = RedbarnScriptEngineManager.getResourceAsString("scripts/model-binder.js");
        String markup =
                "<html>\n" +
                "  <body>\n" +
                "    <div class=\"change\">foo</div>\n" +
                "  </body>\n" +
                "</html>";
        String name = "foo";
        String bindFunction = "function bind() { $('.change').text('bar'); }";
        binder = binder.replace("%redbarnName%", name);
        binder = binder.replace("'%Replace with model binding functions%';", bindFunction);
        ScriptObjectMirror redbarn = (ScriptObjectMirror) scriptEngine.eval(binder);
        redbarn.callMember("markup", markup);
        String actual = (String) redbarn.callMember("html");
        String expected =
                "<html>\n" +
                "  <body>\n" +
                "    <div class=\"change\">bar</div>\n" +
                "  </body>\n" +
                "</html>";
        Assert.assertEquals(actual, expected);
    }
}
