package com.example.kekodweekoneproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kekodweekoneproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class OptimismFragment : Fragment() {

    private val viewModel: OptimismViewModel by viewModels()
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_optimism, container, false)
        val quoteTextView = view.findViewById<TextView>(R.id.quoteTextView)
        val favoriteIcon = view.findViewById<ImageView>(R.id.favoriteIcon)
        val backButton = view.findViewById<ImageView>(R.id.imageView2)

        viewModel.randomQuote.observe(viewLifecycleOwner) { quote ->
            quoteTextView.text = quote
            favoriteIcon.setOnClickListener {
                if (!isFavorite) {
                    viewModel.addToFavorites(quote)
                    favoriteIcon.setImageResource(R.drawable.ic_heart_filled)
                    isFavorite = true
                } else {
                    favoriteIcon.setImageResource(R.drawable.ic_heart)
                    isFavorite = false
                }
            }
        }
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.getRandomQuote()

        return view
    }
}
