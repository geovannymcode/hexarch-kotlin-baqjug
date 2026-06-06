package com.baqjug.catalog.application.port.`in`

import com.baqjug.catalog.domain.model.Product

interface CrudProductUseCase {
    fun create(product: Product)
    fun read(id: String, toCurrency: String?): Product
    fun getAll(toCurrency: String?): List<Product>
}