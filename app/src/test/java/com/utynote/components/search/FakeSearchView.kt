package com.utynote.components.search

import java.util.*

internal class FakeSearchView : SearchContract.View {

    val results: MutableList<SearchContract.ViewModel.Data> = ArrayList()
    val errors: MutableList<SearchContract.ViewModel.Error> = ArrayList()
    val busy: MutableList<SearchContract.ViewModel.Busy> = ArrayList()

    override fun render(model: SearchContract.ViewModel) {
        when (model) {
            is SearchContract.ViewModel.Busy  -> busy.add(model)
            is SearchContract.ViewModel.Error -> errors.add(model)
            is SearchContract.ViewModel.Data  -> results.add(model)
        }
    }
}
