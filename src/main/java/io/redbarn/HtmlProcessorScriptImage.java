package io.redbarn;

import com.google.common.base.Strings;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Exposes functionality in a fully formed html-processor.js script.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class HtmlProcessorScriptImage {

    private ScriptObjectMirror mirror;

    /**
     * Creates a new instance by using a Nashorn script engine to merge a
     * "process" function with the html-processor.js script template.
     *
     * @param scriptEngine The Nashorn script engine into which the fully merged
     *                     html-processor IIFE will be loaded.
     * @param key A unique identifier used to cache the final, merged markup.
     * @param markup The original markup to be processed and converted.
     * @param processFunction The JavaScript "process" function which is used
     *                        to modify the markup sent to the browser.
     */
    public HtmlProcessorScriptImage(
            ScriptEngine scriptEngine,
            String markup,
            String key,
            String processFunction) throws IOException, ScriptException {
        if (scriptEngine == null) {
            throw new IllegalArgumentException("The 'scriptEngine' argument cannot be null.");
        }
        if (Strings.isNullOrEmpty(processFunction)) {
            throw new IllegalArgumentException("The 'processFunction' argument cannot be null or empty.");
        }
        if (Strings.isNullOrEmpty(key)) {
            throw new IllegalArgumentException("The 'key' argument cannot be null or empty.");
        }

        // Merges the model binding script from the template and loads it into
        // the script engine.
        String binder = ResourceUtils.getResourceString("scripts/io/redbarn/html-processor.js");
        binder = binder.replace("'{ProcessFunction}';", processFunction);
        ScriptObjectMirror mirror = (ScriptObjectMirror) scriptEngine.eval(binder);
        this.mirror = mirror;

        // Registers the markup to be transformed.
        Map<String, Object> options = new HashMap<>();
        options.put("useHtmlParser2", true);
        mirror.callMember("markup", markup, options);

        // Stores the model binder in the script engine for later use.
        mirror.callMember("save", key);
    }

    /**
     * Gets an array of parameters listed in the converter script's "process"
     * function.
     *
     * @return A non-empty array of parameters.
     */
    public String[] getParameters() {
        ScriptObjectMirror result = (ScriptObjectMirror) mirror.callMember("params");
        return result.to(String[].class);
    }

    /**
     * Gets the markup found inside the HTML body tag _after_ it has been
     * processed by the markup converter.
     *
     * @param request The HTTP request associated with the conversion.
     * @return The text inside of the HTML body tag.
     */
    public String getBodyMarkup(HttpServletRequest request) {
        String[] params = getParameters();
        Object[] args = ServletUtils.getWebVariables(params, request);
        return (String) mirror.callMember("body", args);
    }

    /**
     * Gets all of the markup processed by the markup converter.
     *
     * @param request The HTTP request associated with the conversion.
     * @return All markup processed by the markup converter.
     */
    public String getHtmlMarkup(HttpServletRequest request) {
        String[] params = getParameters();
        Object[] args = ServletUtils.getWebVariables(params, request);
        return (String) mirror.callMember("html", args);
    }
}
