package com.rahulsrenj.moviecatalogue.serviceapi;

import com.rahulsrenj.moviecatalogue.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieServiceApi {


    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key")String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key")String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key")String apiKey);
}
