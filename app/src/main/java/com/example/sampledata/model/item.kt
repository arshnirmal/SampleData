package com.example.sampledata.model

data class Item(
    val name: String,
    val price: String,
    val image: String,
    val extra: String?
)

data class ApiResponse(
    val status: String,
    val error: String?,
    val data: ItemResponse
)

data class ItemResponse(
    val items: List<Item>
)