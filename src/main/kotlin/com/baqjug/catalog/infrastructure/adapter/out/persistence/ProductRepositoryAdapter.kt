package com.baqjug.catalog.infrastructure.adapter.out.persistence

import com.baqjug.catalog.application.port.out.ProductRepository
import com.baqjug.catalog.domain.model.Product
import org.springframework.stereotype.Component

@Component
class ProductRepositoryAdapter(
    private val jpa: SpringDataProductRepository,
    private val mapper: ProductPersistenceMapper
) : ProductRepository {

    override fun create(product: Product) {
        jpa.save(mapper.toEntity(product))
    }

    override fun read(id: String): Product? =
        jpa.findById(id).orElse(null)?.let { mapper.toDomain(it) }

    override fun getAll(): List<Product> =
        jpa.findAll().map { mapper.toDomain(it) }
}