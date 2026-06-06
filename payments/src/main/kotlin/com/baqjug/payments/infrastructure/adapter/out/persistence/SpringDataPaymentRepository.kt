package com.baqjug.payments.infrastructure.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataPaymentRepository : JpaRepository<PaymentJpaEntity, String>