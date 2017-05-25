package com.utynote.components.search

class SearchViewModel {

    interface Abstract

    class Busy : Abstract

    class Error(val description: String) : Abstract

    class Data(val data: List<SearchItemData>) : Abstract
}
