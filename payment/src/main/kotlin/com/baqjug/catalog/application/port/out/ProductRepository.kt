package com.baqjug.catalog.application.port.out

import com.baqjug.catalog.domain.model.Product

interface ProductRepository {
    fun create(product: Product)
    fun read(id: String): Product?
    fun getAll(): List<Product>
}