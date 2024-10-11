package edu.iesam.dam2024.features.superhero.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroAdapter(
    private val superheroes: List<Superhero>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder>() {

    class SuperheroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.superheroName)
        val imageView: ImageView = itemView.findViewById(R.id.superheroImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_superhero_item, parent, false)
        return SuperheroViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val superhero = superheroes[position]
        holder.imageView.loadUrl(superhero.principalData.imageUrl)
        holder.textView.text = superhero.principalData.name

        holder.itemView.setOnClickListener {
            onItemClick(superhero.principalData.id)
        }
    }

    override fun getItemCount(): Int {
        return superheroes.size
    }
}