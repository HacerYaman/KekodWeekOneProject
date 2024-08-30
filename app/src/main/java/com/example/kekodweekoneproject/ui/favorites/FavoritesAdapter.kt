package com.example.kekodweekoneproject.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.domain.model.FavoriteQuote

class FavoritesAdapter :
    ListAdapter<FavoriteQuote, FavoritesAdapter.FavoriteQuoteViewHolder>(FavoriteQuoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteQuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_quote, parent, false)
        return FavoriteQuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteQuoteViewHolder, position: Int) {
        val favoriteQuote = getItem(position)
        holder.bind(favoriteQuote)
    }

    class FavoriteQuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)

        fun bind(favoriteQuote: FavoriteQuote) {
            quoteTextView.text = favoriteQuote.quote
        }
    }

    class FavoriteQuoteDiffCallback : DiffUtil.ItemCallback<FavoriteQuote>() {
        override fun areItemsTheSame(oldItem: FavoriteQuote, newItem: FavoriteQuote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteQuote, newItem: FavoriteQuote): Boolean {
            return oldItem == newItem
        }
    }
}