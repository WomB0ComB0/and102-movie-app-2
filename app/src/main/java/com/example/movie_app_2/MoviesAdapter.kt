import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
        private val title: TextView = itemView.findViewById(R.id.movie_title)
        private val overview: TextView = itemView.findViewById(R.id.movie_description)
        private val poster: ImageView = itemView.findViewById(R.id.movie_poster)

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
            val radius = 30
            Glide.with(itemView.context)
                .load(movie.mediaImageUrl)
                .transform(RoundedCorners(radius))
                .into(poster)
        }
    }
}
