package com.baqjug.catalog.infrastructure.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "products")
class ProductEntity(
    @Id
    val id: String,
    val name: String,
    @Column(precision = 19, scale = 4)
    val amount: BigDecimal,
    val currency: String
)
