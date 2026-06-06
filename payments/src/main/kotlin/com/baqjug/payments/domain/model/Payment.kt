package com.baqjug.payments.domain.model

data class Payment(
    val id: PaymentId = PaymentId(),
    val amount: Money,
    var status: PaymentStatus = PaymentStatus.PENDING
)