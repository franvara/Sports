package com.franvara.sports.app.main;

import android.support.annotation.UiThread;

import com.franvara.sports.domain.model.Player;

import java.util.List;

@UiThread
public interface IMainView {

    void presentRecyclerView();

    void setupSwipeRefresh();

    void addPlayersToList(List<Player> playerList);

    void showToast(String error);

    void showProgress();

    void hideProgress();
}
