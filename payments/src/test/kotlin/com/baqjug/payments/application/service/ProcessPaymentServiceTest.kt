package com.baqjug.payments.application.service

import com.baqjug.payments.application.port.out.ChargeResult
import com.baqjug.payments.application.port.out.PaymentGateway
import com.baqjug.payments.application.port.out.PaymentRepository
import com.baqjug.payments.domain.model.Money
import com.baqjug.payments.domain.model.Payment
import com.baqjug.payments.domain.model.PaymentStatus
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class FakeGateway(private val approved: Boolean) : PaymentGateway {
    override fun charge(payment: Payment) = ChargeResult(approved, "FAKE-REF")
}

class InMemoryPayments : PaymentRepository {
    private val store = mutableListOf<Payment>()
    override fun save(payment: Payment) { store.add(payment) }
    override fun findAll() = store.toList()
}

class ProcessPaymentServiceTest {

    @Test
    fun `aprueba cuando la pasarela aprueba`() {
        val service = ProcessPaymentService(FakeGateway(approved = true), InMemoryPayments())
        val result = service.process(Payment(amount = Money(BigDecimal("5000"))))
        assertEquals(PaymentStatus.APPROVED, result.status)
    }

    @Test
    fun `rechaza cuando la pasarela rechaza`() {
        val service = ProcessPaymentService(FakeGateway(approved = false), InMemoryPayments())
        val result = service.process(Payment(amount = Money(BigDecimal("5000"))))
        assertEquals(PaymentStatus.REJECTED, result.status)
    }
}