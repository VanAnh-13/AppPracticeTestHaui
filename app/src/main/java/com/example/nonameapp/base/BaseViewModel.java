package com.example.nonameapp.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class BaseViewModel extends ViewModel {
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    protected <T> void executeTask(Callable<DataState<T>> request,
                                   Consumer<T> onSuccess,
                                   Consumer<Exception> onError,
                                   boolean showLoading) {
        if (showLoading) {
            showLoading();
        }
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                DataState<T> response = request.call();
                if (response instanceof DataState.Success) {
                    onSuccess.accept(((DataState.Success<T>) response).getData());
                } else if (response instanceof DataState.Error) {
                    onError.accept(((DataState.Error) response).getException());
                }
            } catch (Exception e) {
                onError.accept(e);
            } finally {
                if (showLoading) {
                    hideLoading();
                }
            }
        });
    }

    public void showLoading() {
        loading.postValue(true);
    }

    public void hideLoading() {
        loading.postValue(false);
    }
}
