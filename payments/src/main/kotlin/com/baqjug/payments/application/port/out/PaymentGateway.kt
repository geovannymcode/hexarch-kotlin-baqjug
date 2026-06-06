package com.baqjug.payments.application.port.out

import com.baqjug.payments.domain.model.Payment

data class ChargeResult(val approved: Boolean, val reference: String?)

interface PaymentGateway {
    fun charge(payment: Payment): ChargeResult
}