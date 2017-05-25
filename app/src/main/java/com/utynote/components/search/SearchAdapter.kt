package com.utynote.components.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.utynote.R
import com.utynote.databinding.SearchViewBusyBinding
import com.utynote.databinding.SearchViewErrorBinding
import com.utynote.databinding.SearchViewItemBinding

internal class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private lateinit var model: SearchViewModel.Abstract

    override fun getItemViewType(position: Int): Int {
        when (model) {
            is SearchViewModel.Busy -> return R.layout.search_view_busy
            is SearchViewModel.Error -> return  R.layout.search_view_error
            else -> return R.layout.search_view_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        when (viewType) {
            R.layout.search_view_busy ->  return ViewHolder(SearchViewBusyBinding.inflate(layoutInflater, parent, false))
            R.layout.search_view_error -> return ViewHolder(SearchViewErrorBinding.inflate(layoutInflater, parent, false))
            else ->                       return ViewHolder(SearchViewItemBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val m = model
        when (m) {
            is SearchViewModel.Busy -> holder.bind()
            is SearchViewModel.Error -> holder.bind(m.description)
            is SearchViewModel.Data -> holder.bind(m.data[position])
        }
    }

    override fun getItemCount(): Int {
        val m = model
        when (m) {
            is SearchViewModel.Data -> return m.data.size
            else -> return 1
        }
    }

    fun setModel(model: SearchViewModel.Abstract) {
        this.model = model
        notifyDataSetChanged()
    }

    internal class ViewHolder : RecyclerView.ViewHolder {
        private var mErrorBinding: SearchViewErrorBinding? = null
        private var mItemBinding: SearchViewItemBinding? = null

        constructor(binding: SearchViewBusyBinding) : super(binding.root)

        constructor(binding: SearchViewErrorBinding) : super(binding.root) {
            mErrorBinding = binding
        }

        constructor(binding: SearchViewItemBinding) : super(binding.root) {
            mItemBinding = binding
        }

        fun bind() {}

        fun bind(model: String) { mErrorBinding!!.model = model }

        fun bind(model: SearchItemData) { mItemBinding!!.model = model }
    }
}
