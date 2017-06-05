package io.redbarn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

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
    public void params_ProcessorFunctionHasNoParameters_ReturnsNonNull()
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
    public void params_ProcessorFunctionHasParameters_ReturnsAllParameters()
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
}
