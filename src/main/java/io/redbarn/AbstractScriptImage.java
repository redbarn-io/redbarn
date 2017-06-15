package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;

/**
 * Used to invoke methods for a specific JavaScript in Java.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public abstract class AbstractScriptImage {

    protected ScriptEngine engine;
    protected ScriptObjectMirror mirror;

    /**
     * Creates a new instance.
     *
     * @param engine A nashorn specific JavaScript engine with the script
     *               being referenced pre-loaded into the global context.
     */
    public AbstractScriptImage(ScriptEngine engine) {
        if (engine == null) {
            String msg = "The 'engine' argument cannot be null";
            throw new IllegalArgumentException(msg);
        }

        this.engine = engine;
        setMirrorFromEngine();
    }

    /**
     * Sets the internal ScriptObjectMirror using the internal ScriptEngine.
     */
    protected abstract void setMirrorFromEngine();

    /**
     * Gets the ScriptObjectMirror created by calling the {@link #setMirrorFromEngine()}
     * method.
     * @return The ScriptObjectMirror used to invoke methods on a JavaScript
     *         type.
     */
    public ScriptObjectMirror getMirror() {
        return mirror;
    }
}
