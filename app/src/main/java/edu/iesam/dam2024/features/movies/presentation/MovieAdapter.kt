package edu.iesam.dam2024.features.movies.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.features.movies.domain.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.superheroName)
        val imageView: ImageView = itemView.findViewById(R.id.superheroImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.imageView.loadUrl(movie.poster)
        holder.textView.text = movie.title

        holder.itemView.setOnClickListener {
            onItemClick(movie.id)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}