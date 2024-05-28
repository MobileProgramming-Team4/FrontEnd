package com.example.mpproject.data



data class StoreItem(
    val itemId: String = "",
    val itemName: String = "",
    val cost: Int = 0,
    val description: String = ""
) {
    constructor() : this("", "", 0, "")
}