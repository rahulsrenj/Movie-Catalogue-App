package com.rahulsrenj.moviecatalogue.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rahulsrenj.moviecatalogue.models.Movie;
import com.rahulsrenj.moviecatalogue.models.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository=new MovieRepository(application);
    }
    public LiveData<List<Movie>> getPopularMovieLiveData(){
        return repository.getPopularMovieLiveData();
    }
    public LiveData<List<Movie>> getTopRatedMoviesLiveData(){
        return repository.getTopRatedMoviesLiveData();
    }
    public LiveData<List<Movie>> getUpcomingMoviesLiveData(){
        return repository.getUpcomingMoviesLiveData();
    }
    public LiveData<List<Movie>> getNowPlayingMoviesLiveData(){
        return repository.getNowPlayingMoviesLiveData();
    }



}
