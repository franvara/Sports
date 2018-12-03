package com.franvara.sports.app.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.franvara.sports.Injector;
import com.franvara.sports.R;
import com.franvara.sports.app.utils.GenericUtils;
import com.franvara.sports.domain.model.Player;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity
        implements IMainView {

    //region Fields & Constants
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_main)
    RecyclerView recyclerView;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fl_progress_container)
    FrameLayout fl_progress_container;

    Unbinder unbinder;
    private MainPresenter mPresenter;
    private MainRVAdapter rvAdapter;

    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mPresenter = new MainPresenter(this, Injector.provideUseCaseHandler(),
                Injector.provideGetPlayersUseCase(this));

        mPresenter.loadPlayers();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //endregion

    //region IMainView implementation

    @Override
    public void presentRecyclerView() {
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        rvAdapter = new MainRVAdapter();
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.loadPlayers();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void addPlayersToList(List<Player> playerList) {
        swipeRefreshLayout.setRefreshing(false);
        rvAdapter.addPlayers(playerList);
        if (!GenericUtils.isNetworkConnectionAvailable(this)) showToast(getString(R.string.no_internet_connection));
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        fl_progress_container.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        fl_progress_container.setVisibility(View.GONE);
    }


    //endregion
}
