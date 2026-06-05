package com.baqjug.catalog.domain.model

data class Product(
    val id: ProductId = ProductId(),
    val name: String,
    var price: Price
) {
    init {
        require(name.isNotBlank()) { "El nombre es obligatorio" }
    }
}