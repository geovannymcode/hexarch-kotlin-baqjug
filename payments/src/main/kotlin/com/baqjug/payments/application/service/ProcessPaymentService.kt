package com.baqjug.payments.application.service

import com.baqjug.payments.application.port.`in`.ProcessPaymentUseCase
import com.baqjug.payments.application.port.out.PaymentGateway
import com.baqjug.payments.application.port.out.PaymentRepository
import com.baqjug.payments.domain.model.Payment
import com.baqjug.payments.domain.model.PaymentStatus
import org.springframework.stereotype.Service

@Service
class ProcessPaymentService(
    private val gateway: PaymentGateway,
    private val repository: PaymentRepository
) : ProcessPaymentUseCase {

    override fun process(payment: Payment): Payment {
        val result = gateway.charge(payment)
        payment.status = if (result.approved) PaymentStatus.APPROVED else PaymentStatus.REJECTED
        repository.save(payment)
        return payment
    }

    override fun findAll(): List<Payment> = repository.findAll()
}