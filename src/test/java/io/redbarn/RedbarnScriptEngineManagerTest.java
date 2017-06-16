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
public class RedbarnScriptEngineManagerTest extends AbstractScriptEngineTest {

    @DataProvider
    public static Object[][] vars() {
        return new Object[][] {
                {"console", true},
        };
    }

    @Test(groups = "Slow")
    public void getScriptEngine_WithNoArguments_ReturnsNonNull()
            throws IOException, ScriptException {
        Assert.assertNotNull(scriptEngine);
    }

    @Test(groups = "Slow", dataProvider = "vars")
    public void getScriptEngine_WithNoArguments_ObjectIsAvailableInScript(String name, boolean notNull)
            throws IOException, ScriptException {
        Object variable = scriptEngine.get(name);
        Assert.assertNotNull(variable);
    }

}
