package io.redbarn;

import com.google.common.base.Stopwatch;
import org.testng.annotations.Test;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.redbarn.TestUtils.assertMarkupEquivalent;
import static io.redbarn.TestUtils.assertRenderSuccess;

/**
 * Tests methods in the TemplateRenderer class.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class TemplateRendererTest extends AbstractScriptEngineTest {

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void constructor_ScriptEngineIsNull_Throws()
            throws IOException, ScriptException {
        TemplateRenderer renderer = new TemplateRenderer(null);
    }

    @Test(groups = "Slow", expectedExceptions = IllegalArgumentException.class)
    public void render_TemplatePathIsNullOrEmpty_Throws()
            throws IOException, ScriptException {
        TemplateRenderer renderer = new TemplateRenderer(scriptEngine);
        renderer.render(null, new Object[0]);
    }

    @Test(groups = "Slow", expectedExceptions = RedbarnException.class)
    public void render_TemplatePathIsNotFound_Throws() throws ScriptException {
        TemplateRenderer renderer = new TemplateRenderer(scriptEngine);
        String templatePath = "foo";
        renderer.render(templatePath, null);
    }

    @Test(groups = "Slow", expectedExceptions = RedbarnException.class)
    public void render_TemplatePathsCorrespondingScriptFileIsNotFound_Throws() throws ScriptException {
        TemplateRenderer renderer = new TemplateRenderer(scriptEngine);
        String templatePath = "templates/no-script-file.html";
        renderer.render(templatePath, null);
    }

    @Test(groups = "Slow")
    public void render_TemplatePathIsValid_Succeeds() throws ScriptException, IOException {
        String templatePath = "templates/foo.html";
        List<Object> args = new ArrayList<>();
        args.add("lorem ipsum dolar at sim.");
        String[] fruit = new String[] {"apples", "pears", "plums"};
        args.add(fruit);
        TemplateRenderer renderer = new TemplateRenderer(scriptEngine);
        assertRenderSuccess(renderer, templatePath, args.toArray());
    }

    @Test(groups = "Slow")
    public void render_ScriptEmploysHelperFunctions_Succeeds() throws ScriptException, IOException {
        String templatePath = "templates/multiple-script-functions.html";
        List<Object> args = new ArrayList<>();
        args.add("lorem ipsum dolar at sim.");
        String[] fruit = new String[] {"apples", "pears", "plums"};
        args.add(fruit);
        TemplateRenderer renderer = new TemplateRenderer(scriptEngine);
        assertRenderSuccess(renderer, templatePath, args.toArray());
    }
}
