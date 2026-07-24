package com.example.nonameapp.base;

import java.util.concurrent.Callable;

public class BaseRepository {
    public <T> DataState<T> getResult(Callable<T> request) {
        try {
            return new DataState.Success<>(request.call());
        } catch (Exception exception) {
            return new DataState.Error(exception);
        }
    }
}
