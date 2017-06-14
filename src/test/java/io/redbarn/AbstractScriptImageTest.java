package io.redbarn;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * Tests concrete methods in the AbstractScriptImage type.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class AbstractScriptImageTest extends AbstractScriptEngineTest {

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void constructor_EngineIsNull_Throws()
            throws IOException, ScriptException {
        LodashScriptImage lodash = new LodashScriptImage(null);
    }
}
