package com.baqjug.catalog.infrastructure.adapter.out.exchange

import com.baqjug.catalog.application.port.out.ExchangeRateClient
import com.baqjug.catalog.domain.model.Price
import org.springframework.stereotype.Component
import java.util.Currency

@Component
class ExchangeRateAdapter(
    private val feign: FxFeignClient
) : ExchangeRateClient {

    override fun convert(price: Price, toCurrency: String): Price {
        val response = feign.convert(price.amount, price.currency.currencyCode, toCurrency)
        return Price(response.amount, Currency.getInstance(response.currency))
    }
}
