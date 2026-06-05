package com.baqjug.catalog.domain.model

import java.math.BigDecimal
import java.util.Currency

data class Price(
    val amount: BigDecimal,
    val currency: Currency = Currency.getInstance("COP")
) {
    init {
        require(amount >= BigDecimal.ZERO) { "El precio no puede ser negativo" }
    }
}