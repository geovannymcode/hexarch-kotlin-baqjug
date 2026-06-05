package com.baqjug.catalog.infrastructure.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "products")
class ProductJpaEntity(
    @Id
    val id: String,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val amount: BigDecimal,
    @Column(nullable = false, length = 3)
    val currency: String
)