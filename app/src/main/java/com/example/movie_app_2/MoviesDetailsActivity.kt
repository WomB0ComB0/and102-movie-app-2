package com.example.movie_app_2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


private const val TAG = "MovieDetailsActivity"

@Suppress("DEPRECATION")
class MoviesDetailsActivity: AppCompatActivity() {
    private lateinit var movieBackdropView: ImageView
    private lateinit var movieTitleView: TextView
    private lateinit var movieReleaseDateView: TextView
    private lateinit var movieDescriptionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_details_activity)

        movieBackdropView = findViewById(R.id.movieBackdrop)
        movieTitleView = findViewById(R.id.movieTitle)
        movieReleaseDateView = findViewById(R.id.movieReleaseDate)
        movieDescriptionView = findViewById(R.id.movieDescription)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as TrendingMovie

        movieTitleView.text = movie.title
        movieReleaseDateView.text = movie.releaseDate
        movieDescriptionView.text = movie.overview

        Glide.with(this)
            .load(movie.mediaBackdropUrl)
            .into(movieBackdropView)

    }

    companion object {
        val MOVIE_EXTRA: String = "MOVIE_EXTRA"
    }
}