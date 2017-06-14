package io.redbarn;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Assists reading resources from the application classpath.
 */
public class ResourceUtils {

    /**
     * Gets the text of the named resource from the classpath.
     *
     * @param resource The classpath to the resource.
     * @return The full text of the resource
     * @throws IOException Thrown if the resource could not be loaded.
     */
    public static String getResourceString(String resource)
            throws IOException {
        String value;
        try (Reader reader = getResourceReader(resource)) {
            if (reader == null) {
                String message = String.format("The resource, '%s', was not found", resource);
                throw new IOException(message);
            }
            value = CharStreams.toString(reader);
        }
        return value;
    }

    /**
     * Gets a Reader from a named classpath resource.
     *
     * @param resource The path to the resource.
     * @return An IO Reader or null if the resource was not found.
     */
    public static Reader getResourceReader(String resource) {
        Reader reader = null;
        Thread currentThread = Thread.currentThread();
        ClassLoader classloader = currentThread.getContextClassLoader();
        InputStream stream = classloader.getResourceAsStream(resource);
        if (stream != null) {
            reader = new InputStreamReader(stream);
        }
        return reader;
    }

    /**
     * Determines if a resource exists in the current classpath.
     *
     * @param resource The classpath to the resource.
     * @return True if the resource does exist.
     */
    public static boolean exists(String resource) {
        boolean exists = true;
        try (Reader reader = getResourceReader(resource)) {
            if (reader == null) {
                exists = false;
            }
        } catch (IOException e) {
            // The exception caught here should be swallowed so that the method
            // returns false gracefully.
        }
        return exists;
    }
}
