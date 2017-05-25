package com.utynote.components.search

class SearchContract {

    interface View {
        fun render(model: SearchViewModel.Abstract)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        fun search(term: String)
    }
}
