package com.utynote.components.search

import java.util.*

internal class FakeSearchView : SearchContract.View {

    val results: MutableList<SearchViewModel.Data> = ArrayList()
    val errors: MutableList<SearchViewModel.Error> = ArrayList()
    val busy: MutableList<SearchViewModel.Busy> = ArrayList()

    override fun render(model: SearchViewModel.Abstract) {
        when (model) {
            is SearchViewModel.Busy ->  busy.add(model)
            is SearchViewModel.Error ->  errors.add(model)
            is SearchViewModel.Data -> results.add(model)
        }
    }
}
