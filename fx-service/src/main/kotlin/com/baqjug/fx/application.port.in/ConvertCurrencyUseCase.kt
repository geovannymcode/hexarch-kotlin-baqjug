package com.baqjug.fx.application.port.`in`

import java.math.BigDecimal

interface ConvertCurrencyUseCase {
    fun convert(amount: BigDecimal, from: String, to: String): BigDecimal
}