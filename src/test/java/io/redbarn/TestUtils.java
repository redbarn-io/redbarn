package io.redbarn;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Exposes utilities useful for working with Redbarn's automated tests.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class TestUtils {

    /**
     * An assertion to prove that two markup strings are the same despite case
     * and whitespace / line breaks between tags.
     *
     * @param actual The markup that you want to prove from, say, a test.
     * @param expected The markup you expect.
     */
    public static void assertMarkupEquivalent(String actual, String expected) {
        boolean equivalent = isMarkupEquivalent(actual, expected);
        assertTrue(equivalent);
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
