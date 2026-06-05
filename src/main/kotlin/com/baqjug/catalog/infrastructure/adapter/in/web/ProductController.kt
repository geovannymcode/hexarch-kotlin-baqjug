package com.baqjug.catalog.infrastructure.adapter.`in`.web

import com.baqjug.catalog.application.port.`in`.CrudProductUseCase
import com.baqjug.catalog.infrastructure.adapter.`in`.web.dto.ProductRequest
import com.baqjug.catalog.infrastructure.adapter.`in`.web.dto.ProductResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val useCase: CrudProductUseCase
) {
    @GetMapping
    fun getAll(@RequestParam(required = false) toCurrency: String?): List<ProductResponse> =
        useCase.getAll(toCurrency).map { ProductResponse.from(it) }

    @GetMapping("/{id}")
    fun read(@PathVariable id: String, @RequestParam(required = false) toCurrency: String?): ProductResponse =
        ProductResponse.from(useCase.read(id, toCurrency))

    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest): ResponseEntity<Void> {
        useCase.create(request.toDomain())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}