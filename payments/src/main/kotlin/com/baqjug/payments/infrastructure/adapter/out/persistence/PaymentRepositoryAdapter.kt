package com.baqjug.payments.infrastructure.adapter.out.persistence

import com.baqjug.payments.application.port.out.PaymentRepository
import com.baqjug.payments.domain.model.Payment
import org.springframework.stereotype.Component

@Component
class PaymentRepositoryAdapter(
    private val jpa: SpringDataPaymentRepository,
    private val mapper: PaymentPersistenceMapper
) : PaymentRepository {
    override fun save(payment: Payment) { jpa.save(mapper.toEntity(payment)) }
    override fun findAll(): List<Payment> = jpa.findAll().map { mapper.toDomain(it) }
}