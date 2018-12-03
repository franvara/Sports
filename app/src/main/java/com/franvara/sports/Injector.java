package com.franvara.sports;

import android.content.Context;
import android.support.annotation.NonNull;

import com.franvara.sports.data.data_model.entities.mapper.EntityDataMapper;
import com.franvara.sports.data.repository.DataProvider;
import com.franvara.sports.data.repository.local.LocalDataSource;
import com.franvara.sports.data.repository.remote.RemoteDataSource;
import com.franvara.sports.domain.threading.UseCaseHandler;
import com.franvara.sports.domain.useCases.GetPlayersUseCase;

public class Injector {

    static DataProvider provideDataSource(@NonNull Context context) {
        return DataProvider.getInstance(
                new RemoteDataSource(),
                new LocalDataSource(context),
                new EntityDataMapper());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static GetPlayersUseCase provideGetPlayersUseCase(Context context) {
        return new GetPlayersUseCase(provideDataSource(context));
    }


}
