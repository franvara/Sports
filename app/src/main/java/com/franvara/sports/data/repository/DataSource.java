package com.franvara.sports.data.repository;

import com.franvara.sports.data.data_model.responses.DataCallback;
import com.franvara.sports.domain.entities.BaseError;
import com.franvara.sports.domain.model.Player;

import java.util.List;

public interface DataSource {

    void getPlayers(DataCallback<List<Player>, BaseError> callback);
}
