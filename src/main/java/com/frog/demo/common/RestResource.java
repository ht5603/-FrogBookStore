package com.frog.demo.common;

import java.util.List;

public class RestResource<T> {

    private List<T> items;
    private Integer sucessCount;
    private String errMsg;

    public List<T> getItems() {
        return items;
    }

    public RestResource<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public Integer getSucessCount() {
        return sucessCount;
    }

    public RestResource<T> setSucessCount(Integer sucessCount) {
        this.sucessCount = sucessCount;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RestResource<T> setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
