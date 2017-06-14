package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.script.ScriptException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Tests methods in the LodashScriptImage class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class LodashScriptImageTest extends AbstractScriptEngineTest {

    @Test(groups = "Slow")
    public void constructor_EngineIsNotNull_MirrorIsSet() {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        ScriptObjectMirror mirror = lodash.getMirror();
        assertNotNull(mirror);
    }

    @Test(groups = "Slow")
    public void getUuid_Always_Succeeds() {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        String uuid = lodash.getUuid();
        assertNotNull(uuid);
    }

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void getParameters_FunctionIsNull_Throws() {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        lodash.getParameters(null);
    }

    @Test(groups = "Slow")
    public void getParameters_FunctionIsValidWithNoParameters_ReturnsNonNull() throws ScriptException {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        String definition = "function render() { }";
        ScriptObjectMirror function = (ScriptObjectMirror) scriptEngine.eval(definition);
        String[] params = lodash.getParameters(function);
        assertNotNull(params);
    }

    @Test(groups = "Slow")
    public void getParameters_FunctionIsValidWithParameters_ReturnsAllParameters() throws ScriptException {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        String definition = "function render(foo, bar, baz) { }";
        ScriptObjectMirror function = (ScriptObjectMirror) scriptEngine.eval(definition);
        String[] params = lodash.getParameters(function);
        assertEquals(params.length, 3);
    }
}
