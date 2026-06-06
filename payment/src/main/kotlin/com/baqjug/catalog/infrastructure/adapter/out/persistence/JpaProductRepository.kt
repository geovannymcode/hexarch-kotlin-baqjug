package com.baqjug.catalog.infrastructure.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface JpaProductRepository : JpaRepository<ProductEntity, String>
