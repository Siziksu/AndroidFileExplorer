package com.siziksu.explorer.common.functions;

/**
 * Function that returns an {@link Throwable}.
 */
public interface Fail {

    /**
     * This method will be executed if the action fails.
     *
     * @param throwable the throwable returned
     */
    void fail(Throwable throwable);
}
