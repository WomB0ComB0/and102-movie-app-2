import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app_2.R
import com.example.movie_app_2.TrendingMovie
import com.example.movie_app_2.MoviesDetailsActivity

class MoviesAdapter(
    private val context: Context,
    private var movies: List<TrendingMovie> = emptyList()
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val title: TextView = itemView.findViewById(R.id.movieTitle)
        private val overview: TextView = itemView.findViewById(R.id.movieDescription)
        private val poster: ImageView = itemView.findViewById(R.id.movieBackdrop)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val movie = movies[absoluteAdapterPosition]
            val intent = Intent(context, MoviesDetailsActivity::class.java).apply {
                putExtra(MoviesDetailsActivity.MOVIE_EXTRA, movie)
            }
            context.startActivity(intent)
        }

        fun bind(movie: TrendingMovie) {
            title.text = movie.title
            overview.text = movie.overview
            Glide.with(itemView.context)
                .load(movie.mediaImageUrl)
                .into(poster)
        }
    }
}

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.movie_app_2.databinding.ItemMovieBinding
//
//class MoviesAdapter(private val movies: List<TrendingMovie>) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
//
//    inner class MoviesViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(movie: TrendingMovie) {
//            binding.movieTitle.text = movie.title
//            binding.movieDescription.text = movie.overview
//            // Load image using Glide or Picasso here
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
//        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MoviesViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
//        holder.bind(movies[position])
//    }
//
//    override fun getItemCount() = movies.size
//}