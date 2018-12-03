package com.franvara.sports.data.repository.local;

import android.content.Context;

import com.franvara.sports.data.data_model.entities.PlayerEntity;
import com.franvara.sports.data.repository.local.daos.PlayerDao;

import java.util.List;

public class LocalDataSource implements ILocalDataSource {

    //region Fields
    private final Context context;
    private static PlayerDao playerDao;

    //endregion

    //region Constructor

    public LocalDataSource(Context context) {
        this.context = context;
        AppDatabase database = AppDatabase.getInstance(this.context.getApplicationContext());
        playerDao = database.getPlayersDao();
    }

    //endregion


    //region ILocalDataSource implementation
    @Override
    public void savePlayers(List<PlayerEntity> players) {
        playerDao.bulkInsert(players);
    }

    @Override
    public List<PlayerEntity> getPlayers() {
        return playerDao.getAll();
    }

    //endregion
}
