package com.baqjug.payments.infrastructure.adapter.out.gateway

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "provider-b", url = "\${providers.b.url}")
interface ProviderBClient {
    @PostMapping("/v2/charges")
    fun charge(@RequestBody request: ChargeApiRequest): ChargeApiResponse
}