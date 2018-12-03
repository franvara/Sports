package com.franvara.sports.data.repository.remote;

import com.franvara.sports.data.data_model.entities.SportEntity;
import com.franvara.sports.data.data_model.responses.DataCallback;
import com.franvara.sports.domain.entities.BaseError;

import java.util.List;

public interface IRemoteDataSource {
    void getPlayers(DataCallback<List<SportEntity>, BaseError> callback);
}