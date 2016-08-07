package com.siziksu.explorer.common.functions;


/**
 * Function that returns a response.
 */
public interface Success<O> {

    /**
     * This method will be executed if the action ends successfully.
     *
     * @param response the response of the action
     */
    void success(O response);
}
