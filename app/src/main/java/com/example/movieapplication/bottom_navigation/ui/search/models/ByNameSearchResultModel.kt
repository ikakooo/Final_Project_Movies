package com.example.movieapplication.bottom_navigation.ui.search.models

data class ByNameSearchResultModel(
    val page: Int,
    val results: List<SearchResultModelResultList>?,
    val total_pages: Int,
    val total_results: Int
)