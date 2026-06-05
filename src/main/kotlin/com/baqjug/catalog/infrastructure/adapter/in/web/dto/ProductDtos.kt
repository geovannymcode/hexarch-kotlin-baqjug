package com.baqjug.catalog.infrastructure.adapter.`in`.web.dto

import com.baqjug.catalog.domain.model.Price
import com.baqjug.catalog.domain.model.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class ProductRequest(
    @field:NotBlank val name: String,
    @field:Positive val amount: BigDecimal
) {
    fun toDomain() = Product(name = name, price = Price(amount))
}

data class ProductResponse(
    val id: String,
    val name: String,
    val amount: BigDecimal,
    val currency: String
) {
    companion object {
        fun from(p: Product) = ProductResponse(
            id = p.id.value,
            name = p.name,
            amount = p.price.amount,
            currency = p.price.currency.currencyCode
        )
    }
}