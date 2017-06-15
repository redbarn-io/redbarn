package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Tests methods in the LodashScriptImage class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class CheerioScriptImageTest extends AbstractScriptEngineTest {

    @Test(groups = "Slow")
    public void constructor_EngineIsNotNull_MirrorIsSet() {
        CheerioScriptImage cheerio = new CheerioScriptImage(scriptEngine);
        ScriptObjectMirror mirror = cheerio.getMirror();
        assertNotNull(mirror);
    }

    @Test(groups = "Slow")
    public void getDom_MarkupIsNull_ReturnsNonNull() {
        CheerioScriptImage cheerio = new CheerioScriptImage(scriptEngine);
        String markup = "<html><body><div>foo</div></body></html>";
        ScriptObjectMirror dom = cheerio.getDom(markup, null);

        // See if the $.markup extension has been loaded.
        boolean actual = dom.hasMember("markup");
        assertEquals(actual, true);
    }

}
