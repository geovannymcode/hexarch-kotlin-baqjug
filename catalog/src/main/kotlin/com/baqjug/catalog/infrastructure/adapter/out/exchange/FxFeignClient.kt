package com.baqjug.catalog.infrastructure.adapter.out.exchange

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal

data class ConvertResponse(val amount: BigDecimal, val currency: String)

@FeignClient(name = "fx-service", url = "\${fx.url}")
interface FxFeignClient {
    @GetMapping("/convert")
    fun convert(
        @RequestParam amount: BigDecimal,
        @RequestParam from: String,
        @RequestParam to: String
    ): ConvertResponse
}
