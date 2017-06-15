package io.redbarn;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;

import javax.script.ScriptException;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.*;

/**
 * Exposes utilities useful for working with Redbarn's automated tests.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class TestUtils {

    public static final Logger LOG = getLogger(TestUtils.class);

    public static void assertRenderSuccess(
            TemplateRenderer renderer,
            String templatePath,
            Object[] args) throws ScriptException, IOException {

        Stopwatch watch = Stopwatch.createStarted();
        String actual = renderer.render(templatePath, args);
        watch.stop();
        LOG.info("Render time for {}: {}", templatePath, watch);
        String expectedPath = templatePath.replace(".html", ".expected.html");
        String expected = ResourceUtils.getResourceString(expectedPath);
        assertMarkupEquivalent(actual, expected);
    }

    /**
     * An assertion to prove that two markup strings are the same despite case
     * and whitespace / line breaks between tags.
     *
     * @param actual The markup that you want to prove from, say, a test.
     * @param expected The markup you expect.
     */
    public static void assertMarkupEquivalent(String actual, String expected) {
        actual = actual.toLowerCase();
        actual = minifyMarkup(actual);
        expected = expected.toLowerCase();
        expected = minifyMarkup(expected);
        assertEquals(actual, expected);
    }

    /**
     * Determines if two markup strings are the same despite case and
     * whitespace / line breaks between tags.
     *
     * @param actual The markup that you want to prove from, say, a test.
     * @param expected The markup you expect.
     * @return true if the two markup strings contain similar tags and content.
     */
    public static boolean isMarkupEquivalent(String actual, String expected) {
        actual = actual.toLowerCase();
        actual = minifyMarkup(actual);
        expected = expected.toLowerCase();
        expected = minifyMarkup(expected);
        return actual.equals(expected);
    }

    /**
     * Removes all whitespace and line breaks in an HTML or other tag based
     * string.
     *
     * @param markup The markup to minify.
     * @return The minified markup.
     */
    public static String minifyMarkup(String markup) {
        return markup.replaceAll(">\\s+",">").replaceAll("\\s+<","<");
    }
}
