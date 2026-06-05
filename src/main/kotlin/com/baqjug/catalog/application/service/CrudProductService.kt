package com.baqjug.catalog.application.service

import com.baqjug.catalog.application.port.`in`.CrudProductUseCase
import com.baqjug.catalog.application.port.out.ExchangeRateClient
import com.baqjug.catalog.application.port.out.ProductRepository
import com.baqjug.catalog.domain.exception.ProductNotFoundException
import com.baqjug.catalog.domain.model.Product
import org.springframework.stereotype.Service

@Service
class CrudProductService(
    private val repository: ProductRepository,
    private val exchangeRate: ExchangeRateClient
) : CrudProductUseCase {

    override fun create(product: Product) = repository.create(product)

    override fun read(id: String, toCurrency: String?): Product {
        val product = repository.read(id) ?: throw ProductNotFoundException(id)
        if (toCurrency != null) {
            product.price = exchangeRate.convert(product.price, toCurrency)
        }
        return product
    }

    override fun getAll(toCurrency: String?): List<Product> {
        val products = repository.getAll()
        if (toCurrency != null) {
            products.forEach { it.price = exchangeRate.convert(it.price, toCurrency) }
        }
        return products
    }
}