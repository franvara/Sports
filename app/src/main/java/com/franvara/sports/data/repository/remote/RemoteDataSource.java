package com.franvara.sports.data.repository.remote;

import android.support.annotation.NonNull;

import com.franvara.sports.BuildConfig;
import com.franvara.sports.data.data_model.entities.SportEntity;
import com.franvara.sports.data.data_model.responses.DataCallback;
import com.franvara.sports.data.utils.LogUtils;
import com.franvara.sports.domain.entities.BaseError;
import com.franvara.sports.domain.threading.UseCaseHandler;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements IRemoteDataSource {

    //region Fields

    private ApiServices apiServices = null;

    //endregion

    //region Constructor

    public RemoteDataSource() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.LOG_ENABLED) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .callbackExecutor(UseCaseHandler.getThreadPoolExecutor())
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    //endregion

    //region IRemoteDataSource implementation
    @Override
    public void getPlayers(DataCallback<List<SportEntity>, BaseError> callback) {
        Call<List<SportEntity>> call = apiServices.getPlayers();
        try {
            call.enqueue(new Callback<List<SportEntity>>() {
                @Override
                public void onResponse(@NonNull Call<List<SportEntity>> call,
                                       @NonNull Response<List<SportEntity>> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                        return;
                    }

                    callback.onFailure(new BaseError(response.code(), response.message()));
                }

                @Override
                public void onFailure(@NonNull Call<List<SportEntity>> call,
                                      @NonNull Throwable t) {
                    callback.onFailure(BaseError.getErrorEstandar());
                }
            });
        } catch (Exception e) {
            callback.onFailure(BaseError.getErrorEstandar());
            LogUtils.LogDebug("Exception", e.getMessage());
        }
    }
    //endregion
}
