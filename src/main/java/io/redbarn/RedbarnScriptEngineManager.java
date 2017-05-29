package io.redbarn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Loads all scripts and prepares the ScriptEngine.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class RedbarnScriptEngineManager extends ScriptEngineManager {

    public static final String SCRIPT_ENGINE_NAME = "nashorn";
    private static final Logger logger =
            LoggerFactory.getLogger(RedbarnScriptEngineManager.class);

    /**
     * Gets a JavaScript script engine preloaded with scripts needed for
     * template translation.
     * @return A ScriptEngine instance.
     */
    public ScriptEngine getScriptEngine() {
        ScriptEngine engine = getEngineByName(SCRIPT_ENGINE_NAME);
        return engine;
    }
}
