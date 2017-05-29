package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.Assert;
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
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Assert.assertNotNull(engine);
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_GlobalKeywordIsAvailableInScript()
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Object global = engine.get("global");
        Assert.assertNotNull(global);
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_ConsoleIsAvailableInScript()
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Object console = engine.get("console");
        Assert.assertNotNull(console);
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_LodashIsAvailableInScript()
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Object lodash = engine.get("_");
        Assert.assertNotNull(lodash);
    }

    @Test(groups = "Unit", dataProvider = "mixins")
    public void getScriptEngine_WithNoArguments_LodashMixinIsAvailableInScript(String name, boolean notNull)
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        ScriptObjectMirror lodash = (ScriptObjectMirror) engine.get("_");
        Object mixin = lodash.get(name);
        boolean actual = mixin != null;
        Assert.assertEquals(notNull, actual);
    }
}
