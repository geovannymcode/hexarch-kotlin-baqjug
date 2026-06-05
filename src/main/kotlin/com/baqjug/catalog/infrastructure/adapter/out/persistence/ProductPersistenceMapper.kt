package com.baqjug.catalog.infrastructure.adapter.out.persistence

import com.baqjug.catalog.domain.model.Price
import com.baqjug.catalog.domain.model.Product
import com.baqjug.catalog.domain.model.ProductId
import org.springframework.stereotype.Component
import java.util.Currency

@Component
class ProductPersistenceMapper {

    fun toEntity(product: Product) = ProductJpaEntity(
        id = product.id.value,
        name = product.name,
        amount = product.price.amount,
        currency = product.price.currency.currencyCode
    )

    fun toDomain(entity: ProductJpaEntity) = Product(
        id = ProductId(entity.id),
        name = entity.name,
        price = Price(entity.amount, Currency.getInstance(entity.currency))
    )
}