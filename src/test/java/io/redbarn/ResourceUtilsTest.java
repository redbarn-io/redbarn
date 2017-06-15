package io.redbarn;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * Tests methods in the Resource Utils class.
 */
public class ResourceUtilsTest {

    @Test(groups = "Fast", expectedExceptions = IOException.class)
    public void getResourceString_ResourceNotFound_Throws() throws IOException {
        ResourceUtils.getResourceString("foo/bar.baz");
    }

    @Test(groups = "Fast")
    public void getResourceString_ResourceFound_ReturnsResourceAsString() throws IOException {
        String text = ResourceUtils.getResourceString("scripts/io/redbarn/redbarn-bundle.js");
        assertNotNull(text);
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceNotFound_ReturnsNull() {
        Reader reader = ResourceUtils.getResourceReader("foo/bar.baz");
        assertNull(reader);
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceFound_ReturnsReader() throws IOException {
        String resource = RedbarnScriptEngineManager.REDBARN_JAVASCRIPT_BUNDLE_RESOURCE;
        Reader reader = ResourceUtils.getResourceReader(resource);
        assertNotNull(reader);
        reader.close();
    }

    @Test(groups = "Fast")
    public void exists_ResourceFound_ReturnsTrue() {
        String resource = RedbarnScriptEngineManager.REDBARN_JAVASCRIPT_BUNDLE_RESOURCE;
        boolean actual = ResourceUtils.exists(resource);
        assertEquals(actual, true);
    }

    @Test(groups = "Fast")
    public void exists_ResourceNotFound_ReturnsFalse() {
        boolean actual = ResourceUtils.exists("foo");
        assertEquals(actual, false);
    }
}
