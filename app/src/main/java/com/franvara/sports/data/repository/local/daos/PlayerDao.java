package com.franvara.sports.data.repository.local.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.franvara.sports.data.data_model.entities.PlayerEntity;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<PlayerEntity> characters);

    @Query("SELECT * FROM Players")
    List<PlayerEntity> getAll();

    @Query("DELETE FROM Players")
    void clear();

}
