package com.baqjug.payments.infrastructure.adapter.out.gateway

import com.baqjug.payments.application.port.out.ChargeResult
import com.baqjug.payments.application.port.out.PaymentGateway
import com.baqjug.payments.domain.model.Payment
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class ProviderBAdapter(
    private val client: ProviderBClient
) : PaymentGateway {
    override fun charge(payment: Payment): ChargeResult {
        val response = client.charge(ChargeApiRequest(payment.amount.value))
        return ChargeResult(response.approved, response.reference)
    }
}