package com.franvara.sports.data.repository.remote;

import com.franvara.sports.data.data_model.entities.SportEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("bins/66851")
    Call<List<SportEntity>> getPlayers();

}
