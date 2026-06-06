package com.baqjug.providersim

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

data class ChargeRequest(val amount: BigDecimal)
data class ChargeResponse(val approved: Boolean, val reference: String)

@RestController
class ChargeController {
    @PostMapping("/charge")
    fun charge(@RequestBody req: ChargeRequest): ChargeResponse =
        ChargeResponse(approved = req.amount <= BigDecimal("1000000"), reference = UUID.randomUUID().toString())
}