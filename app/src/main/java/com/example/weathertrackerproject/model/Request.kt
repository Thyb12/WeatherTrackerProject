package com.example.weathertrackerproject.model

data class Request(
    val type: String,
    val query: String,
    val language: String,
    val unit: String
) {
    constructor() : this("", "", "", "")
}

