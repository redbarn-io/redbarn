package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import java.util.Map;

/**
 * Used to invoke Cheerio.js methods in Java.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class CheerioScriptImage extends AbstractScriptImage {

    public static final String CHEERIO_VARIABLE = "cheerio";

    /**
     * Creates a new instance.
     *
     * @param engine A Nashorn specific JavaScript engine with Cheerio.js
     *               pre-loaded into the global context as 'cheerio'.
     */
    public CheerioScriptImage(ScriptEngine engine) {
        super(engine);
    }

    /**
     * See {@link AbstractScriptImage#setMirrorFromEngine()}
     */
    @Override
    protected void setMirrorFromEngine() {
        mirror = (ScriptObjectMirror) engine.get(CHEERIO_VARIABLE);
    }

    /**
     * Gets a jQuery like interface for manipulating the supplied HTML from
     * a Cheerio.js instance.  This is intended to be the argument to the "$"
     * parameter of template "render" methods.
     *
     * @param markup The markup to be "loaded" into a cheerio "DOM" instance.
     * @param options Options passed directly to Cheerio's "load" method.
     *                These options include, among other things the
     *                'useHtmlParser2' setting.  Please see cheerio.js.org for
     *                more details on specific options.
     * @return A reference to the jQuery like, Cheerio DOM object.
     */
    public ScriptObjectMirror getDom(String markup, Map<String, Object> options) {
        ScriptObjectMirror dom;
        String functionName = "reload";
        if (options == null) {
            dom = (ScriptObjectMirror) mirror.callMember(functionName, markup);
        } else {
            dom = (ScriptObjectMirror) mirror.callMember(functionName, markup, options);
        }
        return dom;
    }
}
