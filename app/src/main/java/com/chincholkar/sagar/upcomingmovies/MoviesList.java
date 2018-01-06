package com.chincholkar.sagar.upcomingmovies;

/**
 * Created by mangal on 12/18/2017.
 */

public class MoviesList {

         private String id;
         private String title;
         private String date;
         private String poster_path;
         private String adult;
         private String Overview;
    private String vote_average;

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }



    public MoviesList(String id, String title, String poster_path, String adult,String date,String overview, String vote_average) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.adult = adult;
        this.date = date;
        this.Overview = overview;
        this.vote_average = vote_average;
    }



    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }




    public MoviesList(String id, String title, String poster_path, String adult,String date,String overview) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.adult = adult;
        this.date = date;
        this.Overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




    public MoviesList(String id, String title, String poster_path, String adult) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.adult = adult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }
}
