package com.utynote.components.search

sealed class SearchViewModel {

    class Busy : SearchViewModel()

    class Error(val description: String) : SearchViewModel()

    class Data(val data: List<SearchItemData>) : SearchViewModel()
}