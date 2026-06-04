package com.baqjug.fx.application.service

import com.baqjug.fx.application.port.`in`.ConvertCurrencyUseCase
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ConvertCurrencyService : ConvertCurrencyUseCase {

    private val ratesFromCop = mapOf(
        "USD" to BigDecimal("0.00025"),
        "EUR" to BigDecimal("0.00023")
    )

    override fun convert(amount: BigDecimal, from: String, to: String): BigDecimal {
        if (from == to) return amount
        val rate = ratesFromCop[to] ?: error("Moneda no soportada: $to")
        return amount.multiply(rate)
    }
}