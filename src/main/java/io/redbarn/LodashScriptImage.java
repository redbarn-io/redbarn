package io.redbarn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;

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
     * @param engine A nashorn specific JavaScript engine with Lodash
     *               pre-loaded into the global context as '_';
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
}
