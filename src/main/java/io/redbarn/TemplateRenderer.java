package io.redbarn;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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

    /**
     * Renders the HTML template modified by the render function found in the
     * template's corresponding JavaScript file.
     *
     * @param templatePath The classpath to the containing markup to be
     *                     modified.
     * @param arguments An array of arguments which should be passed to the
     *                  render function in the template's JavaScript file.
     * @return The HTML markup generated by the render function in the
     *         template's JavaScript file.
     *
     * @throws IllegalArgumentException Thrown when the templatePath argument is
     *                                  null or empty.
     * @throws RedbarnException Thrown when either the template or its
     *                          corresponding JavaScript could not be found in
     *                          the classpath.
     * @throws ScriptException Thrown when Nashorn registers an error.
     */
    public String render(String templatePath, Object[] arguments)
            throws ScriptException {
        if (Strings.isNullOrEmpty(templatePath)) {
            String msg = "The 'templatePath' argument cannot be null or empty.";
            throw new IllegalArgumentException(msg);
        }
        if (!ResourceUtils.exists(templatePath)) {
            String msg = "The specified template '%s' could not be found " +
                         "as a classpath resource.";
            msg = String.format(msg, templatePath);
            throw new RedbarnException(msg);
        }
        String scriptPath = templatePath + ".js";
        if (!ResourceUtils.exists(scriptPath)) {
            String msg = "The specified script '%s' could not be found " +
                    "as a classpath resource.";
            msg = String.format(msg, scriptPath);
            throw new RedbarnException(msg);
        }

        String markup = null;
        try {
            markup = ResourceUtils.getResourceString(templatePath);
            String script = ResourceUtils.getResourceString(scriptPath);
            ScriptObjectMirror mirror = (ScriptObjectMirror) scriptEngine.eval(script);
            List<Object> args = Lists.newArrayList(arguments);
            args.add(markup);
            markup = (String) mirror.call(null, args.toArray());
        } catch (IOException e) {
            // Since we checked that these scripts existed already, this should
            // never happen.  It should be safe to ignore this exception.
        }
        return markup;
    }
}
