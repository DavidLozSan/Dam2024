package edu.iesam.dam2024.features.superhero.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroDetailActivity : AppCompatActivity() {

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var viewModel: SuperheroDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero_detail)

        superheroFactory = SuperheroFactory(this)
        viewModel = superheroFactory.buildSuperheroDetailViewModel()

        getSuperheroId()?.let { superheroId ->
            viewModel.viewCreated(superheroId)?.let { superhero ->
                binData(superhero)
            }
        }
    }

    private fun getSuperheroId(): String? {
        return intent.getStringExtra(KEY_SUPERHERO_ID)
    }

    private fun binData(superhero: Superhero) {
        val imageView = findViewById<ImageView>(R.id.url)
        imageView.loadUrl(superhero.principalData.imageUrl)
    }

    companion object {
        val KEY_SUPERHERO_ID = "key_superhero_id"

        fun getIntent(context: Context, superheroId: String): Intent {
            val intent = Intent(context, SuperheroDetailActivity::class.java)
            intent.putExtra(KEY_SUPERHERO_ID, superheroId)
            return intent
        }
    }
}