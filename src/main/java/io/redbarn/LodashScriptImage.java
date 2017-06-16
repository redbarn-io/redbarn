package io.redbarn;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to invoke Lodash methods in Java.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class LodashScriptImage extends AbstractScriptImage {

    public static final String LODASH_VARIABLE = "_";

    /**
     * Creates a new instance.
     *
     * @param engine A Nashorn specific JavaScript engine with Lodash
     *               pre-loaded into the global context as '_'.
     */
    public LodashScriptImage(ScriptEngine engine) {
        super(engine);
    }

    /**
     * See {@link AbstractScriptImage#setMirrorFromEngine()}
     */
    @Override
    protected void setMirrorFromEngine() {
        mirror = (ScriptObjectMirror) engine.get(LODASH_VARIABLE);
    }

    /**
     * Gets a universally unique ID from lodash's uuid mixin.
     *
     * @return A UUID computed using timestamps.
     */
    public String getUuid() {
        return (String) mirror.callMember("uuid");
    }

    /**
     * Gets an array of parameters found in a JavaScript function.  For example,
     * given the following script
     *
     * function render(foo, bar, baz) {
     *     console.log(foo + bar + baz);
     * }
     *
     * this method will return ["foo", "bar", "baz"].
     *
     * @param function A reference to a JavaScript function in Nashorn.
     * @return A non-null String[], even if the JavaScript function has no
     *         parameters.
     */
    public String[] getParameters(ScriptObjectMirror function) {
        if (function == null) {
            String msg = "The 'function' argument cannot be null.";
            throw new IllegalArgumentException(msg);
        }

        String[] params;
        try {
            Object result = mirror.callMember("getParams", function);
            ScriptObjectMirror array = (ScriptObjectMirror) result;
            params = array.to(String[].class);
        } catch (Exception ex) {
            String msg = "The argument you supplied for 'function' is invalid.";
            throw new IllegalArgumentException(msg);
        }
        return params;
    }
}
