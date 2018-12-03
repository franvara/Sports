package com.franvara.sports.domain.model;

public class Player {
    private String date;
    private String image;
    private String surname;
    private String name;
    private String sport;

    public Player(String name, String surname, String sport, String date, String image) {
        this.date = date;
        this.image = image;
        this.surname = surname;
        this.name = name;
        this.sport = sport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
