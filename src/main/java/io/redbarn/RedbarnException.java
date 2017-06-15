package io.redbarn;

/**
 * An exception to be thrown when an error which is very specific to the
 * processing of Redbarn templates occurs.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
public class RedbarnException extends RuntimeException {

    /**
     * Creates a new instance.
     *
     * @param message A friendly message to let the downstream programmer know
     *                what happened.
     */
    public RedbarnException(String message) {
        super(message);
    }

    /**
     * Creates a new instance.
     *
     * @param message A friendly message to let the downstream programmer know
     *                what happened.
     * @param cause The underlying cause of the exception.
     */
    public RedbarnException(String message, Throwable cause) {
        super(message, cause);
    }

}
