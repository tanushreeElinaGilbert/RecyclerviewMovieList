package com.androidapp.alivetch.movies;

/**
 * Created by user on 6/27/2018.
 */

public class MovieContent {
    private String imgURL, movieTitle, director;
    private int rating;

    public MovieContent(String movieTitle, String director, String imgurl) {
        this.movieTitle = movieTitle;
        this.director = director;
        this.imgURL = imgurl;

    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getDirector() {
        return director;
    }


}
