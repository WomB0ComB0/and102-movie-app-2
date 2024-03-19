package com.example.movie_app_2

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TrendingMoviesResponse(
    @SerialName("results")
    val results: List<TrendingMovie>? = null
)

@Keep
@Parcelize
@Serializable
data class TrendingMovie(
    @SerialName("title")
    val title: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null
) : Parcelable {
    val mediaImageUrl: String
        get() = createImageUrl(posterPath)

    val mediaBackdropUrl: String
        get() = createImageUrl(backdropPath)

    private fun createImageUrl(path: String?): String {
        return "https://image.tmdb.org/t/p/w500/$%7Bpath.orEmpty()%7D"
    }
}