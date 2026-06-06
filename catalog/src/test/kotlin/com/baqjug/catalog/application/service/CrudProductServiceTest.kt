package com.baqjug.catalog.application.service

import com.baqjug.catalog.application.port.out.ExchangeRateClient
import com.baqjug.catalog.application.port.out.ProductRepository
import com.baqjug.catalog.domain.model.Price
import com.baqjug.catalog.domain.model.Product
import java.math.BigDecimal
import java.util.Currency
import kotlin.test.Test
import kotlin.test.assertEquals

class InMemoryProductRepository : ProductRepository {
    private val store = linkedMapOf<String, Product>()
    override fun create(product: Product) { store[product.id.value] = product }
    override fun read(id: String) = store[id]
    override fun getAll() = store.values.toList()
}

class FakeExchangeRateClient : ExchangeRateClient {
    override fun convert(price: Price, toCurrency: String) =
        Price(price.amount * BigDecimal("0.00025"), Currency.getInstance(toCurrency))
}

class CrudProductServiceTest {

    private val service = CrudProductService(
        InMemoryProductRepository(),
        FakeExchangeRateClient()
    )

    @Test
    fun `convierte el precio a la moneda pedida`() {
        service.create(Product(name = "Cafe", price = Price(BigDecimal("32000"))))
        val result = service.getAll(toCurrency = "USD")
        assertEquals("USD", result.first().price.currency.currencyCode)
    }
}