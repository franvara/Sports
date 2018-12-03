package com.franvara.sports.app.main;

import com.franvara.sports.domain.model.Player;
import com.franvara.sports.domain.threading.UseCase;
import com.franvara.sports.domain.threading.UseCaseHandler;
import com.franvara.sports.domain.useCases.GetPlayersUseCase;

import java.util.ArrayList;
import java.util.List;

final class MainPresenter {

    //region Fields

    private IMainView mView;
    private UseCaseHandler useCaseHandler;

    private List<Player> playerList;

    private GetPlayersUseCase getPlayersUseCase;

    //endregion

    //region Constructor
    MainPresenter(IMainView mView, UseCaseHandler useCaseHandler,
                  GetPlayersUseCase getPlayersUseCase) {
        this.mView = mView;
        this.useCaseHandler = useCaseHandler;
        this.getPlayersUseCase = getPlayersUseCase;

        playerList = new ArrayList<>();

        mView.presentRecyclerView();
        mView.setupSwipeRefresh();
    }
    //endregion

    //region MainPresenter implementation

    void loadPlayers() {
        mView.showProgress();

        useCaseHandler.execute(getPlayersUseCase, null, new UseCase.UseCaseCallback<GetPlayersUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetPlayersUseCase.ResponseValue response) {
                mView.hideProgress();
                playerList = response.getPlayerList();
                mView.addPlayersToList(playerList);
            }

            @Override
            public void onError(String error) {
                mView.hideProgress();
                mView.showToast(error);
            }
        });
    }

    //endregion
}
