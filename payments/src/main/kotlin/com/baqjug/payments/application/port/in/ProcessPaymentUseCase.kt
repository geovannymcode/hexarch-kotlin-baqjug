package com.baqjug.payments.application.port.`in`

import com.baqjug.payments.domain.model.Payment

interface ProcessPaymentUseCase {
    fun process(payment: Payment): Payment
    fun findAll(): List<Payment>
}