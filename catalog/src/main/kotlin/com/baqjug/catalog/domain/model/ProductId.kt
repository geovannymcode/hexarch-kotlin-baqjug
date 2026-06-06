package com.baqjug.catalog.domain.model

import java.util.UUID

@JvmInline
value class ProductId(val value: String = UUID.randomUUID().toString())