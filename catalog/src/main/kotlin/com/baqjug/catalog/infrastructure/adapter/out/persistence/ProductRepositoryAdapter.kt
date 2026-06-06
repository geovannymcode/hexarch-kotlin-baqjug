package com.baqjug.catalog.infrastructure.adapter.out.persistence

import com.baqjug.catalog.application.port.out.ProductRepository
import com.baqjug.catalog.domain.model.Price
import com.baqjug.catalog.domain.model.Product
import com.baqjug.catalog.domain.model.ProductId
import org.springframework.stereotype.Component
import java.util.Currency

@Component
class ProductRepositoryAdapter(
    private val jpa: JpaProductRepository
) : ProductRepository {

    override fun create(product: Product) {
        jpa.save(toEntity(product))
    }

    override fun read(id: String): Product? {
        return jpa.findById(id).map { toDomain(it) }.orElse(null)
    }

    override fun getAll(): List<Product> {
        return jpa.findAll().map { toDomain(it) }
    }

    private fun toEntity(product: Product) = ProductEntity(
        id = product.id.value,
        name = product.name,
        amount = product.price.amount,
        currency = product.price.currency.currencyCode
    )

    private fun toDomain(entity: ProductEntity) = Product(
        id = ProductId(entity.id),
        name = entity.name,
        price = Price(entity.amount, Currency.getInstance(entity.currency))
    )
}
