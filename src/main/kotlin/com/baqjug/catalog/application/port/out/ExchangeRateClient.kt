package com.baqjug.catalog.application.port.out

import com.baqjug.catalog.domain.model.Price

interface ExchangeRateClient {
    fun convert(price: Price, toCurrency: String): Price
}