package com.baqjug.payments.infrastructure.adapter.out.gateway

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.math.BigDecimal

data class ChargeApiRequest(val amount: BigDecimal)
data class ChargeApiResponse(val approved: Boolean, val reference: String)

@FeignClient(name = "provider-a", url = "\${providers.a.url}")
interface ProviderAClient {
    @PostMapping("/charge")
    fun charge(@RequestBody request: ChargeApiRequest): ChargeApiResponse
}