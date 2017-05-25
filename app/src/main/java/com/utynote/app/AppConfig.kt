package com.utynote.app


data class AppConfig(val search: AppConfig.Search) {

    data class Search(val server: String, val format: String)

}
