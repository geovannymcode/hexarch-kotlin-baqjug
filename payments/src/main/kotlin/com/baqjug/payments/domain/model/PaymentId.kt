package com.baqjug.payments.domain.model

import java.util.UUID

@JvmInline
value class PaymentId(val value: String = UUID.randomUUID().toString())