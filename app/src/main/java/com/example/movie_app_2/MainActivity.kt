package com.example.movie_app_2

import MoviesAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.movie_app_2.databinding.ActivityMainBinding
import com.google.gson.JsonSyntaxException
import kotlinx.serialization.json.Json
import okhttp3.Headers

private enum class TimeWindow {
    WEEK,
    DAY
}

private const val TAG = "MainActivity"
private val time_window: TimeWindow = if ("week" === "week") TimeWindow.WEEK else TimeWindow.DAY
class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<TrendingMovie>()
    private lateinit var binding: ActivityMainBinding
    private val API_KEY: String = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val movieAdapter = MoviesAdapter(this, movies)
        binding.Rv.adapter = movieAdapter

        binding.Rv.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            binding.Rv.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client["https://api.themoviedb.org/3/trending/movie/week?api_key=$API_KEY", object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch movies: $statusCode")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched movies: $json")
                try {
                    val jsonString = json.jsonObject.toString()
                    val parsedJson = createJson(jsonString)

                    parsedJson.results?.let { movies.addAll(it) }
                    parsedJson.results?.let {
                        movies.addAll(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                } catch (e: JsonSyntaxException) {
                    Log.e(TAG, "Error parsing JSON: ${e.message}")
                }
            }

            private fun createJson(jsonString: String): TrendingMoviesResponse {
                val json = Json { ignoreUnknownKeys = true }
                return json.decodeFromString(TrendingMoviesResponse.serializer(), jsonString)
            }
        }]
    }
}