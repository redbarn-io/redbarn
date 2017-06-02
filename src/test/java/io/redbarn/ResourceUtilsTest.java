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
        String text = ResourceUtils.getResourceString("scripts/redbarn.js");
        Assert.assertNotNull(text);
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceNotFound_ReturnsNull() {
        Reader reader = ResourceUtils.getResourceReader("foo/bar.baz");
        Assert.assertNull(reader);
    }

    @Test(groups = "Fast")
    public void getResourceReader_ResourceFound_ReturnsReader() throws IOException {
        Reader reader = ResourceUtils.getResourceReader("scripts/redbarn.js");
        Assert.assertNotNull(reader);
        reader.close();
    }
}
