package com.franvara.sports.data.data_model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SportEntity {

    @SerializedName("players")
    private List<PlayerEntity> players;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
