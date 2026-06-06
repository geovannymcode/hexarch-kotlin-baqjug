package com.baqjug.payments.infrastructure.adapter.`in`.web

import com.baqjug.payments.application.port.`in`.ProcessPaymentUseCase
import com.baqjug.payments.infrastructure.adapter.`in`.web.dto.PaymentRequest
import com.baqjug.payments.infrastructure.adapter.`in`.web.dto.PaymentResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentController(
    private val useCase: ProcessPaymentUseCase
) {
    @PostMapping
    fun create(@Valid @RequestBody request: PaymentRequest): PaymentResponse =
        PaymentResponse.from(useCase.process(request.toDomain()))

    @GetMapping
    fun list(): List<PaymentResponse> =
        useCase.findAll().map { PaymentResponse.from(it) }
}
