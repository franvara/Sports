package com.franvara.sports.data.data_model.entities.mapper;

import com.franvara.sports.data.data_model.entities.PlayerEntity;
import com.franvara.sports.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public class EntityDataMapper {

    public EntityDataMapper() {

    }

    private Player transform(PlayerEntity playerEntity) {
        if (playerEntity != null) {
            return new Player(playerEntity.getName(), playerEntity.getSurname(),
                    playerEntity.getSport(), playerEntity.getDate(), playerEntity.getImage());
        }
        return null;
    }

    public List<Player> transformToPlayerList(List<PlayerEntity> playerEntityList) {
        final List<Player> wordList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            final Player player = transform(playerEntity);
            if (player != null) {
                wordList.add(player);
            }
        }
        return wordList;
    }


}
