package com.example.kekodweekoneproject.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kekodweekoneproject.R

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = FavoritesAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.favoriteQuotes.observe(viewLifecycleOwner) { quotes ->
            adapter.submitList(quotes)
        }

        return view
    }
}