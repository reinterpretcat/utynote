package com.utynote.components.search.data


import com.utynote.entities.Place

data class SearchResult(val term: String,
                        val places: Iterable<Place>)
