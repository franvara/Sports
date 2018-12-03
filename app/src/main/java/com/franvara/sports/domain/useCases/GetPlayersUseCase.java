package com.franvara.sports.domain.useCases;

import com.franvara.sports.data.data_model.responses.DataCallback;
import com.franvara.sports.data.repository.DataSource;
import com.franvara.sports.data.utils.LogUtils;
import com.franvara.sports.domain.entities.BaseError;
import com.franvara.sports.domain.model.Player;
import com.franvara.sports.domain.threading.UseCase;

import java.util.List;

public class GetPlayersUseCase extends UseCase<GetPlayersUseCase.RequestValues, GetPlayersUseCase.ResponseValue> {

    private final DataSource dataSource;

    public GetPlayersUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getPlayers(new DataCallback<List<Player>, BaseError>() {
            @Override
            public void onSuccess(List<Player> wordList) {
                LogUtils.LogDebug("GetPlayersUC", "onGetPlayersSuccess");

                //Ejecutamos el callback del caso de uso: recuperamos el valor
                getUseCaseCallback().onSuccess(new ResponseValue(wordList));
            }


            @Override
            public void onFailure(BaseError errorBase) {
                LogUtils.LogDebug("GetPlayersUC", "onGetPlayersFailure");
                getUseCaseCallback().onError(errorBase.getMensajeError());
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Player> playerList;

        public ResponseValue(List<Player> playerList) {
            this.playerList = playerList;
        }

        public List<Player> getPlayerList() {
            return this.playerList;
        }
    }

}