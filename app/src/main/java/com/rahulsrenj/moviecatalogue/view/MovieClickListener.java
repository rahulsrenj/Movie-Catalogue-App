package com.rahulsrenj.moviecatalogue.view;

import android.view.View;

import com.rahulsrenj.moviecatalogue.models.Movie;

public interface MovieClickListener {

    public void onMovieClicked(View view, Movie movie);
}
