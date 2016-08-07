package com.siziksu.explorer.common.functions;

/**
 * Function that returns a result and may throw an exception.
 * <br />It is designed ot be executed by another thread.
 * <br />It returns the result of the async request through: {@code action(O)}.
 *
 * @param <O> the type of the result of the function
 */
public interface Action<O> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    O action() throws Exception;
}
