package com.example.chala.inclass06_chalasani;

import java.util.ArrayList;

/**
 * Created by chala on 2/20/2017.
 */

public class Details {
    String title;
    String image;
    String overview;
    String genre;
    String pub;
    String you;
    ArrayList<String> sim;
    String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<String> getSim() {
        return sim;
    }

    public void setSim(ArrayList<String> sim) {
        this.sim = sim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getYou() {
        return you;
    }

    public void setYou(String you) {
        this.you = you;
    }

    @Override
    public String toString() {
        return "Details{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", overview='" + overview + '\'' +
                ", genre='" + genre + '\'' +
                ", pub='" + pub + '\'' +
                ", you='" + you + '\'' +
                ", sim=" + sim +
                ", logo='" + logo + '\'' +
                '}';
    }
}
