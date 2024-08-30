package com.example.kekodweekoneproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kekodweekoneproject.R

class RespectFragment : Fragment() {

    private val viewModel: RespectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_respect, container, false)
        val quoteTextView = view.findViewById<TextView>(R.id.quoteTextView)

        viewModel.randomQuote.observe(viewLifecycleOwner) { quote ->
            quoteTextView.text = quote
        }

        viewModel.getRandomQuote()

        return view
    }
}
