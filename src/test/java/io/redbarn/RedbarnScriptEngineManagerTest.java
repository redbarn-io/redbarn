package io.redbarn;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;

/**
 * Tests methods in the RedbarnScriptEngineManager class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class RedbarnScriptEngineManagerTest {

    @Test(groups = "Unit")
    public void getScriptEngine_WithNoArguments_ReturnsNonNull() {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        ScriptEngine engine = manager.getScriptEngine();
        Assert.assertNotNull(engine);
    }
}
