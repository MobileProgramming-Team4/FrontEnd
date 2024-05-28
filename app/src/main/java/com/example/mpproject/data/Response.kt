package com.example.mpproject.data


data class Response(
    val userId: String = "",
    val selectedOption: String = "",
    val responseDate: String = ""
) {
    constructor() : this("", "", "")
}