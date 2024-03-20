package com.example.movie_app_2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


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

        val movie = intent.getParcelableExtra<TrendingMovie>(MOVIE_EXTRA)
        if (movie != null) {
            movieTitleView.text = movie.title
            movieReleaseDateView.text = movie.releaseDate
            movieDescriptionView.text = movie.overview

            val radius = 30;
            Glide.with(this)
                .load(movie.mediaBackdropUrl)
                .centerCrop()
                .transform(RoundedCorners(radius))
                .into(movieBackdropView)
        } else {
            Toast.makeText(this, "Movie details not available", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        const val MOVIE_EXTRA: String = "MOVIE_EXTRA"
    }
}