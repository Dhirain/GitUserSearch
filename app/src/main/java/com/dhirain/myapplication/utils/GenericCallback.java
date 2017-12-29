package com.dhirain.myapplication.utils;

/**
 * Created by royzer on 1/5/17.
 */

public interface GenericCallback<T> {
    void onRequestSuccess(T objectToReturn);
    void onRequestFailure(Throwable error, String errorMessage);
}
