package com.baqjug.payments.infrastructure.adapter.`in`.web.dto

import com.baqjug.payments.domain.model.Money
import com.baqjug.payments.domain.model.Payment
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class PaymentRequest(@field:Positive val amount: BigDecimal) {
    fun toDomain() = Payment(amount = Money(amount))
}

data class PaymentResponse(val id: String, val amount: BigDecimal, val currency: String, val status: String) {
    companion object {
        fun from(p: Payment) =
            PaymentResponse(p.id.value, p.amount.value, p.amount.currency, p.status.name)
    }
}