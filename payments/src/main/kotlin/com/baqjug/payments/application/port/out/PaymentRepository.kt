package com.baqjug.payments.application.port.out

import com.baqjug.payments.domain.model.Payment

interface PaymentRepository {
    fun save(payment: Payment)
    fun findAll(): List<Payment>
}