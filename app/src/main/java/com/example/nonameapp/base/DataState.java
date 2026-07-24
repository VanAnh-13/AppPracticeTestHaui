package com.example.nonameapp.base;

public abstract class DataState<T> {
    private DataState() {}

    public static final class Success<T> extends DataState<T> {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public static final class Error extends DataState<Object> {
        private final Exception exception;

        public Error(Exception exception) {
            this.exception = exception;
        }

        public Exception getException() {
            return exception;
        }
    }
}
