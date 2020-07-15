package com.example.movieapplication.bottom_navigation.search.models

data class SearchResultModelByName(
    val page: Int,
    val results: List<SearchResultModelResultList>?,
    val total_pages: Int,
    val total_results: Int
)