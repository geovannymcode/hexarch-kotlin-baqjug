package com.baqjug.catalog.infrastructure.adapter.`in`.web

import com.baqjug.catalog.application.port.`in`.CrudProductUseCase
import com.baqjug.catalog.domain.model.Price
import com.baqjug.catalog.domain.model.Product
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

data class CreateProductRequest(
    @field:NotBlank(message = "El nombre es obligatorio")
    val name: String,
    @field:Positive(message = "El monto debe ser positivo")
    val amount: BigDecimal
)

data class ProductResponse(
    val id: String,
    val name: String,
    val amount: BigDecimal,
    val currency: String
)

@RestController
@RequestMapping("/products")
class ProductController(
    private val useCase: CrudProductUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateProductRequest): ProductResponse {
        val product = Product(name = request.name, price = Price(request.amount))
        useCase.create(product)
        return toResponse(product)
    }

    @GetMapping
    fun getAll(@RequestParam(required = false) toCurrency: String?): List<ProductResponse> {
        return useCase.getAll(toCurrency).map { toResponse(it) }
    }

    private fun toResponse(product: Product) = ProductResponse(
        id = product.id.value,
        name = product.name,
        amount = product.price.amount,
        currency = product.price.currency.currencyCode
    )
}
