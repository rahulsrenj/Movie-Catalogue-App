/*

    Author: Rahul S Renjith

 */

package com.rahulsrenj.moviecatalogue;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.rahulsrenj.moviecatalogue.databinding.ActivityMainBinding;
import com.rahulsrenj.moviecatalogue.models.Movie;
import com.rahulsrenj.moviecatalogue.view.ImageSliderAdapter;
import com.rahulsrenj.moviecatalogue.view.MovieClickListener;
import com.rahulsrenj.moviecatalogue.view.MovieListAdapter;
import com.rahulsrenj.moviecatalogue.view.MoviePreview;
import com.rahulsrenj.moviecatalogue.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;


/*
    Add the TMDB API key in the strings.xml
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MovieListAdapter popularAdapter,upcomingAdapter,topRatedAdapter,nowPlayingAdapter;
    private MovieViewModel viewModel;
    private RecyclerView popularMovieRecyclerView;
    private RecyclerView upcomingMoviesRecyclerView;
    private RecyclerView nowPlayingRecyclerView;
    private RecyclerView topRatedRecyclerView;
    private ImageSliderAdapter imageSliderAdapter;
    private ViewPager2 imageSlider;

    private final Handler handler=new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel= new ViewModelProvider(this).get(MovieViewModel.class);
        popularMovieRecyclerView=binding.popularRecyclerView;
        upcomingMoviesRecyclerView=binding.upcomingMoviesRecyclerView;
        nowPlayingRecyclerView=binding.nowPlayingRecyclerView;
        topRatedRecyclerView=binding.topRatedRecyclerView;
        imageSlider=binding.imageSlider;

        viewModel.getErrorState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                {
                    binding.main.setVisibility(View.GONE);
                    binding.errorState.setVisibility(View.VISIBLE);
                    Snackbar.make(binding.getRoot(),"Failed to load",Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMovieData();
                        }
                    }).show();
                }
                else{
                    binding.main.setVisibility(View.VISIBLE);
                    binding.errorState.setVisibility(View.GONE);
                }
            }
        });

        loadMovieData();


    }
    public void getPopularMovies(){
        popularAdapter=new MovieListAdapter(new ArrayList<>());
        popularMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        popularMovieRecyclerView.setAdapter(popularAdapter);
        popularAdapter.setMovieClickListener(new MovieClickListener() {
            @Override
            public void onMovieClicked(View view, Movie movie) {
                showMovieDetails(movie);
            }
        });

        viewModel.getPopularMovieLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                popularAdapter.updateData((ArrayList<Movie>) movies);
            }
        });


    }

    public void getUpcomingMovies(){
        upcomingAdapter=new MovieListAdapter(new ArrayList<>());
        upcomingMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        upcomingMoviesRecyclerView.setAdapter(upcomingAdapter);
        upcomingAdapter.setMovieClickListener(new MovieClickListener() {
            @Override
            public void onMovieClicked(View view, Movie movie) {
                showMovieDetails(movie);
            }
        });
        viewModel.getUpcomingMoviesLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                upcomingAdapter.updateData((ArrayList<Movie>) movies);
            }
        });

    }
    public void getNowPlayingMovies(){
        nowPlayingAdapter=new MovieListAdapter(new ArrayList<>());
        nowPlayingRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        nowPlayingRecyclerView.setAdapter(nowPlayingAdapter);

        nowPlayingAdapter.setMovieClickListener(new MovieClickListener() {
            @Override
            public void onMovieClicked(View view, Movie movie) {
                showMovieDetails(movie);
            }
        });
        imageSliderAdapter = new ImageSliderAdapter(new ArrayList<>());
        imageSlider.setAdapter(imageSliderAdapter);
        viewModel.getNowPlayingMoviesLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                nowPlayingAdapter.updateData((ArrayList<Movie>) movies);
                imageSliderAdapter.updateData((ArrayList<Movie>) movies);

            }
        });
        if (runnable==null)
        {
            runnable=new Runnable() {
                @Override
                public void run() {
                    if (imageSliderAdapter.getItemCount()>0)
                    {
                        int nextItem= imageSlider.getCurrentItem()+1;
                        if (nextItem>=imageSliderAdapter.getItemCount())
                        {
                            nextItem=0;
                        }
                        imageSlider.setCurrentItem(nextItem,true);
                        //we need to call this to start scrolling in a loop
                        handler.postDelayed(this,5000);

                    }
                }
            };
            handler.postDelayed(runnable,5000);
        }

    }
    public void getTopRatedMovies(){
        topRatedAdapter=new MovieListAdapter(new ArrayList<>());
        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        topRatedRecyclerView.setAdapter(topRatedAdapter);

        topRatedAdapter.setMovieClickListener(new MovieClickListener() {
            @Override
            public void onMovieClicked(View view, Movie movie) {
                showMovieDetails(movie);
            }
        });
        viewModel.getTopRatedMoviesLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                topRatedAdapter.updateData((ArrayList<Movie>) movies);
            }
        });
    }

    public void loadMovieData(){
        getPopularMovies();
        getUpcomingMovies();
        getTopRatedMovies();
        getNowPlayingMovies();
    }
    public void showMovieDetails(Movie movie){
        MoviePreview moviePreview=new MoviePreview(movie);
        moviePreview.show(getSupportFragmentManager(),"Movie Preview");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}