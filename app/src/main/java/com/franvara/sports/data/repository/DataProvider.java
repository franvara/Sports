package com.franvara.sports.data.repository;

import com.franvara.sports.data.data_model.entities.PlayerEntity;
import com.franvara.sports.data.data_model.entities.SportEntity;
import com.franvara.sports.data.data_model.entities.mapper.EntityDataMapper;
import com.franvara.sports.data.data_model.responses.DataCallback;
import com.franvara.sports.data.repository.local.ILocalDataSource;
import com.franvara.sports.data.repository.remote.IRemoteDataSource;
import com.franvara.sports.domain.entities.BaseError;
import com.franvara.sports.domain.model.Player;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class DataProvider implements DataSource {

    private static DataProvider INSTANCE = null;
    private final IRemoteDataSource remoteDataSource;
    private final ILocalDataSource localDataSource;

    private final EntityDataMapper entityDataMapper;

    private DataProvider(IRemoteDataSource remoteDataSource,
                         ILocalDataSource localDataSource,
                         EntityDataMapper entityDataMapper) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.entityDataMapper = entityDataMapper;
    }

    /**
     * Devuelve la instancia de la clase, creandola si es necesario.
     *
     * @param remoteDataSource el servicio web.
     * @param localDataSource  el almacenamiento local de datos.
     * @return la instancia actual
     */
    public static synchronized DataProvider getInstance(IRemoteDataSource remoteDataSource,
                                                        ILocalDataSource localDataSource,
                                                        EntityDataMapper entityDataMapper) {

        if (INSTANCE == null) {
            INSTANCE = new DataProvider(remoteDataSource, localDataSource, entityDataMapper);
        }
        return INSTANCE;
    }

    @SuppressWarnings("unused")
    public static void destroyInstance() {
        INSTANCE = null;
    }

    //region DataSource implementation

    private boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void getPlayers(DataCallback<List<Player>, BaseError> callback) {

        if (isInternetAvailable()) {
            remoteDataSource.getPlayers(new DataCallback<List<SportEntity>, BaseError>() {
                @Override
                public void onSuccess(List<SportEntity> response) {
                    List<PlayerEntity> playerList = new ArrayList<>();

                    for (SportEntity sport : response) {
                        for (PlayerEntity player : sport.getPlayers()) {
                            player.setSport(sport.getTitle());
                            playerList.add(player);
                        }
                    }

                    localDataSource.savePlayers(playerList);

                    callback.onSuccess(entityDataMapper.transformToPlayerList(localDataSource.getPlayers()));
                }

                @Override
                public void onFailure(BaseError errorBase) {
                    callback.onFailure(errorBase);
                }
            });
        } else {
            callback.onSuccess(entityDataMapper.transformToPlayerList(localDataSource.getPlayers()));
        }
    }

    //endregion
}
