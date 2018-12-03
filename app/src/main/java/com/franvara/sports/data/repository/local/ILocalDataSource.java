package com.franvara.sports.data.repository.local;

import com.franvara.sports.data.data_model.entities.PlayerEntity;

import java.util.List;

public interface ILocalDataSource {
    void savePlayers(List<PlayerEntity> players);

    List<PlayerEntity> getPlayers();
}
