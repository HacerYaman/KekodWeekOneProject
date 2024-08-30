package com.example.kekodweekoneproject.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.kekodweekoneproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GivingFragment : Fragment() {

    private val viewModel: GivingViewModel by viewModels()
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_giving, container, false)
        val quoteTextView = view.findViewById<TextView>(R.id.quoteTextView)
        val favoriteIcon = view.findViewById<ImageView>(R.id.favoriteIcon)

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

        viewModel.getRandomQuote()

        return view
    }
}