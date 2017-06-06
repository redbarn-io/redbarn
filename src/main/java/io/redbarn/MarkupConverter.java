package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Handles the conversion of markup.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class MarkupConverter {

    public static String toMarkup(
            ScriptEngine scriptEngine,
            String markup,
            String processFunction,
            String key,
            HttpServletRequest request) throws IOException, ScriptException {

        // Merges the model binding script from the template and loads it into
        // the script engine.
        String binder = ResourceUtils.getResourceString("scripts/html-processor.js");
        binder = binder.replace("'{ProcessFunction}';", processFunction);
        ScriptObjectMirror mirror = (ScriptObjectMirror) scriptEngine.eval(binder);

        // Registers the markup to be transformed.
        mirror.callMember("markup", markup);

        // Stores the model binder in the script engine for later use.
        mirror.callMember("save", key);

        // Gets the results of the model binder.
        String[] params = new String[] { "foo", "bar", "baz" };
        Object[] args = ServletUtils.getWebVariables(params, request);

        return (String) mirror.callMember("html", args);
    }

}
