package com.rahulsrenj.moviecatalogue.models;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahulsrenj.moviecatalogue.R;
import com.rahulsrenj.moviecatalogue.serviceapi.MovieServiceApi;
import com.rahulsrenj.moviecatalogue.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movie> movieArrayList;
    private Application application;
    private MutableLiveData<List<Movie>> popularMovieLiveData=new MutableLiveData<>();
    private MutableLiveData<List<Movie>> upcomingMoviesLiveData=new MutableLiveData<>();
    private MutableLiveData<List<Movie>> topRatedMoviesLiveData=new MutableLiveData<>();
    private MutableLiveData<List<Movie>> nowPlayingMoviesLiveData=new MutableLiveData<>();
    private MutableLiveData<Boolean> errorState=new MutableLiveData<>();

    public MovieRepository(Application application)
    {
        this.application=application;
    }

    public MutableLiveData<List<Movie>> getPopularMovieLiveData(){
        MovieServiceApi serviceApi= RetrofitInstance.getService();
        Call<MovieResponse> call=serviceApi.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse=response.body();
                if (movieResponse!=null && movieResponse.getResults()!=null )
                {
                    movieArrayList= (ArrayList<Movie>) movieResponse.getResults();
                    popularMovieLiveData.setValue(movieArrayList);
                    errorState.setValue(false);
                }else {
                    errorState.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                errorState.setValue(true);
            }
        });

        return popularMovieLiveData;
    }

    public MutableLiveData<List<Movie>> getTopRatedMoviesLiveData(){
        MovieServiceApi serviceApi=RetrofitInstance.getService();
        Call<MovieResponse> call=serviceApi.getTopRatedMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse=response.body();
                if (movieResponse!=null && movieResponse.getResults()!=null)
                {
                    movieArrayList= (ArrayList<Movie>) movieResponse.getResults();
                    topRatedMoviesLiveData.setValue(movieArrayList);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return topRatedMoviesLiveData;
    }

    public MutableLiveData<List<Movie>> getUpcomingMoviesLiveData(){
        MovieServiceApi serviceApi=RetrofitInstance.getService();
        Call<MovieResponse> call=serviceApi.getUpcomingMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse=response.body();
                if (movieResponse!=null && movieResponse.getResults()!=null)
                {
                    movieArrayList= (ArrayList<Movie>) movieResponse.getResults();
                    upcomingMoviesLiveData.setValue(movieArrayList);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return upcomingMoviesLiveData;
    }
    public MutableLiveData<List<Movie>> getNowPlayingMoviesLiveData(){
        MovieServiceApi serviceApi=RetrofitInstance.getService();
        Call<MovieResponse> call=serviceApi.getNowPlayingMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse=response.body();
                if (movieResponse!=null && movieResponse.getResults()!=null)
                {
                    movieArrayList= (ArrayList<Movie>) movieResponse.getResults();
                    nowPlayingMoviesLiveData.setValue(movieArrayList);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return nowPlayingMoviesLiveData;
    }

    public LiveData<Boolean> getErrorState()
    {
        return errorState;
    }



}
