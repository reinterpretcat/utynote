package com.utynote.components.search

class SearchContract {

    interface View {
        fun render(model: ViewModel)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        fun search(term: String)
    }

    interface ViewModel {

        class Busy : ViewModel

        class Error(val description: String) : ViewModel

        class Data(val data: List<SearchItemData>) : ViewModel
    }
}
