package io.redbarn;

import org.testng.annotations.BeforeClass;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * A base class for test types which use the Redbarn specific ScriptEngine.
 */
public class AbstractScriptEngineTest {

    protected ScriptEngine scriptEngine;

    @BeforeClass(groups = "Slow")
    public void classSetup() throws IOException, ScriptException {
        RedbarnScriptEngineManager manager = new RedbarnScriptEngineManager();
        scriptEngine = manager.getScriptEngine();
    }
}
