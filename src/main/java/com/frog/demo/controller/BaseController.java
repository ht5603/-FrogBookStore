package com.frog.demo.controller;

import com.frog.demo.common.RestResource;
import com.frog.demo.exception.ParamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Wrap and return the response information(including http status and result).
 */
public abstract class BaseController {

    // return query result
    protected <T> ResponseEntity<RestResource<T>> success(List<T> items) {
        return fill(HttpStatus.OK, items, items.size(), null);
    }

    // return the count of update or create operation
    protected <T> ResponseEntity<RestResource<T>> success(int sucessCount) {
        return fill(HttpStatus.OK, null , sucessCount, null);
    }

    /**
     * Return error message depends on
     * @param e
     * @param <T>
     * @return
     */
    protected <T> ResponseEntity<RestResource<T>> fail(Exception e) {
        HttpStatus status = null;
        String errMsg = null;
        if (e instanceof ParamException) {
            status = HttpStatus.BAD_REQUEST;
            errMsg = e.getMessage();
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errMsg = e.getMessage();
        }

        return fill(status, null, null, errMsg);
    }


    protected <T> ResponseEntity<RestResource<T>> fill(HttpStatus status, List<T> items, Integer successCount, String message) {
        RestResource body = new RestResource();
        body.setItems(items);
        body.setSucessCount(successCount);
        body.setErrMsg(message);
        ResponseEntity<RestResource<T>> entity = new ResponseEntity<RestResource<T>>(body, status);
        return entity;
    }
}
