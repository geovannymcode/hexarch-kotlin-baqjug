package com.baqjug.payments.infrastructure.adapter.out.persistence

import com.baqjug.payments.domain.model.Money
import com.baqjug.payments.domain.model.Payment
import com.baqjug.payments.domain.model.PaymentId
import com.baqjug.payments.domain.model.PaymentStatus
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class PaymentPersistenceMapper {
    fun toEntity(p: Payment) =
        PaymentJpaEntity(p.id.value, p.amount.value, p.amount.currency, p.status.name)

    fun toDomain(e: PaymentJpaEntity) = Payment(
        id = PaymentId(e.id),
        amount = Money(e.amount, e.currency),
        status = PaymentStatus.valueOf(e.status)
    )
}