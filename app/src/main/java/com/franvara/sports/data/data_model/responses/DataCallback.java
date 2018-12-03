package com.franvara.sports.data.data_model.responses;

public interface DataCallback<R, E> {
    void onSuccess(R response);
    void onFailure(E errorBase);
}
