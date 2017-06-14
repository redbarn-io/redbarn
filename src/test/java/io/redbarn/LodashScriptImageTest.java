package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Tests methods in the LodashScriptImage class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class LodashScriptImageTest extends AbstractScriptEngineTest {

    @Test
    public void constructor_EngineIsNotNull_MirrorIsSet() {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        ScriptObjectMirror mirror = lodash.getMirror();
        assertNotNull(mirror);
    }

    @Test
    public void getUuid_Always_Succeeds() {
        LodashScriptImage lodash = new LodashScriptImage(scriptEngine);
        String uuid = lodash.getUuid();
        assertNotNull(uuid);
    }
}
