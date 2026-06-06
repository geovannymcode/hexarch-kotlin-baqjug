package com.baqjug.payments.domain.model

import java.math.BigDecimal

data class Money(
    val value: BigDecimal,
    val currency: String = "COP"
) {
    init {
        require(value > BigDecimal.ZERO) { "El monto debe ser mayor a cero" }
    }
}