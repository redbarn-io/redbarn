package io.redbarn;

import javax.script.ScriptEngine;

/**
 * Handles logic for rendering Redbarn specific HTML templates.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class TemplateRenderer {

    private ScriptEngine scriptEngine;

    /**
     * Creates a new instance.
     *
     * @param scriptEngine A Nashorn JavaScript engine used for processing the
     *                     markup with Cheerio and other tools.
     */
    public TemplateRenderer(ScriptEngine scriptEngine) {
        if (scriptEngine == null) {
            String msg = "The 'scriptEngine' argument cannot be null.";
            throw new IllegalArgumentException(msg);
        }

        this.scriptEngine = scriptEngine;
    }
}
