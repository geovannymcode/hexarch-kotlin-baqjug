package com.baqjug.catalog.infrastructure.adapter.out.exchange

import com.baqjug.catalog.application.port.out.ExchangeRateClient
import com.baqjug.catalog.domain.model.Price
import org.springframework.stereotype.Component
import java.util.Currency

@Component
class StubExchangeRateClient : ExchangeRateClient {
    override fun convert(price: Price, toCurrency: String): Price =
        Price(price.amount, Currency.getInstance(toCurrency))
}