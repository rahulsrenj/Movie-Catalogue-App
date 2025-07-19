package com.rahulsrenj.moviecatalogue.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rahulsrenj.moviecatalogue.R;
import com.rahulsrenj.moviecatalogue.databinding.MovieDetailsLayoutBinding;
import com.rahulsrenj.moviecatalogue.models.Movie;

public class MoviePreview extends BottomSheetDialogFragment {

    private Movie movie;
    public MoviePreview(Movie movie)
    {
        this.movie=movie;
    }
    MovieDetailsLayoutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater,R.layout.movie_details_layout,container,false);
        binding.setMovie(movie);
        return binding.getRoot();
    }

//    @Override
//    public int getTheme() {
//        return R.style.BottomSheetDarkTheme;
//    }
}
