package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        String binder = ResourceUtils.getResourceString("scripts/model-binder.js");
        binder = binder.replace("'{ProcessFunction}';", processFunction);
        ScriptObjectMirror mirror = (ScriptObjectMirror) scriptEngine.eval(binder);

        // Registers the markup to be transformed.
        mirror.callMember("markup", markup);

        // Stores the model binder in the script engine for later use.
        mirror.callMember("saveModelBinder", key);

        // Gets the results of the model binder.
        Object[] args = new Object[3];
        args[0] = request.getAttribute("foo");
        args[1] = request.getAttribute("bar");
        args[2] = request.getAttribute("baz");
        Map<String, Object> options = new HashMap<>();
        options.put("context", request.getServletContext());
        options.put("session", request.getSession());
        options.put("request", request);
        options.put("locale", request.getLocale());
        options.put("params", request.getParameterMap());
        args[3] = options;

        return (String) mirror.callMember("html", args);
    }

}
