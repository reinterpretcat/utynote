package com.utynote.components.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utynote.R
import com.utynote.databinding.SearchViewBusyBinding
import com.utynote.databinding.SearchViewErrorBinding
import com.utynote.databinding.SearchViewItemBinding
import com.utynote.extensions.Either
import com.utynote.extensions.getOrThrow

typealias EitherBinding = Either<SearchViewBusyBinding, SearchViewItemBinding, SearchViewErrorBinding>

internal class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var model: SearchViewModel? = null

    override fun getItemViewType(position: Int): Int  = when (model) {
        is SearchViewModel.Busy -> R.layout.search_view_busy
        is SearchViewModel.Error -> R.layout.search_view_error
        else -> R.layout.search_view_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.search_view_busy -> ViewHolder(Either.First<SearchViewBusyBinding>(SearchViewBusyBinding.inflate(inflater, parent, false)))
            R.layout.search_view_error -> ViewHolder(Either.Third<SearchViewErrorBinding>(SearchViewErrorBinding.inflate(inflater, parent, false)))
            else -> ViewHolder(Either.Second<SearchViewItemBinding>(SearchViewItemBinding.inflate(inflater, parent, false)))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(model!!, position)

    override fun getItemCount(): Int {
        val m = model
        when (m) {
            is SearchViewModel.Data -> return m.data.size
            null -> return 0
            else -> return 1
        }
    }

    fun setModel(model: SearchViewModel) {
        this.model = model
        notifyDataSetChanged()
    }

    internal class ViewHolder(val binding : EitherBinding) : RecyclerView.ViewHolder(binding.fold( {it.root}, {it.root}, {it.root})) {

        fun bind(model : SearchViewModel, position : Int) = when (model) {
            is SearchViewModel.Busy  -> binding.firstProjection().getOrThrow() .model = model.progress
            is SearchViewModel.Data  -> binding.secondProjection().getOrThrow().model = model.data[position]
            is SearchViewModel.Error -> binding.thirdProjection().getOrThrow() .model = model.description
        }
    }
}
