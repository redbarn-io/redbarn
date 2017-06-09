package io.redbarn;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;

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
        Assert.assertNotNull(text);
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceNotFound_ReturnsNull() {
        Reader reader = ResourceUtils.getResourceReader("foo/bar.baz");
        Assert.assertNull(reader);
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceFound_ReturnsReader() throws IOException {
        Reader reader = ResourceUtils.getResourceReader("scripts/io/redbarn/redbarn-bundle.js");
        Assert.assertNotNull(reader);
        reader.close();
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceFound_RetrusnReader() throws IOException {
        String resource = RedbarnScriptEngineManager.REDBARN_JAVASCRIPT_BUNDLE_RESOURCE;
        Reader reader = ResourceUtils.getResourceReader(resource);
        Assert.assertNotNull(reader);
        reader.close();
    }
}
