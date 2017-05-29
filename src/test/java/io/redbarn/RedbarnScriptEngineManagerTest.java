package io.redbarn;

import org.testng.Assert;
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

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_ReturnsNonNull()
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Assert.assertNotNull(engine);
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_GlobalKeywordIsAvailbleInScript()
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Object global = engine.get("global");
        Assert.assertNotNull(global);
    }

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_ConsoleIsAvailbleInScript()
            throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Object console = engine.get("console");
        Assert.assertNotNull(console);
    }
}
