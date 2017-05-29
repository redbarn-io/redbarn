package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * Tests methods in the RedbarnScriptEngineManager class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class RedbarnScriptEngineManagerTest {

    private ScriptEngine scriptEngine = null;

    @BeforeClass
    public void classSetup() throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        scriptEngine = manager.getScriptEngine();
    }

    @DataProvider
    public static Object[][] vars() {
        return new Object[][] {
                {"global", true},
                {"console", true},
                {"_", true},
                {"cheerio", true},
                {"redbarn", true}
        };
    }

    @DataProvider
    public static Object[][] mixins() {
        return new Object[][] {
                {"uuid", true},
                {"replaceAll", true},
                {"getParams", true}
        };
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_ReturnsNonNull()
            throws IOException, ScriptException {
        Assert.assertNotNull(scriptEngine);
    }

    @Test(groups = "Unit", dataProvider = "vars")
    public void getScriptEngine_WithNoArguments_ObjectIsAvailableInScript(String name, boolean notNull)
            throws IOException, ScriptException {
        Object variable = scriptEngine.get(name);
        Assert.assertNotNull(variable);
    }

    @Test(groups = "Unit", dataProvider = "mixins")
    public void getScriptEngine_WithNoArguments_LodashMixinIsAvailableInScript(String name, boolean notNull)
            throws IOException, ScriptException {
        ScriptObjectMirror lodash = (ScriptObjectMirror) scriptEngine.get("_");
        Object mixin = lodash.get(name);
        boolean actual = mixin != null;
        Assert.assertEquals(notNull, actual);
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_ModelBinderCanBeSavedInScript()
            throws IOException, ScriptException {
        String binder = RedbarnScriptEngineManager.getResourceAsString("scripts/model-binder.js");
        String markup = "<html><body><div>foo</div></body></html>";
        String name = "foo";
        String bindFunction = "function bind() { $('div').text('bar'); }";
        binder = binder.replace("%markup%", markup);
        binder = binder.replace("%redbarnName%", name);
        binder = binder.replace("'%Replace with model binding functions%';", bindFunction);
        ScriptObjectMirror redbarn = (ScriptObjectMirror) scriptEngine.eval(binder);
        String bound = (String) redbarn.callMember("html");
        Assert.assertEquals("<div>bar</div>", bound);
    }
}
