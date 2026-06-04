package com.baqjug.fx.adapter.`in`.web

import com.baqjug.fx.application.port.`in`.ConvertCurrencyUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

data class ConvertResponse(val amount: BigDecimal, val currency: String)

@RestController
class FxController(
    private val useCase: ConvertCurrencyUseCase
) {
    @GetMapping("/convert")
    fun convert(
        @RequestParam amount: BigDecimal,
        @RequestParam from: String,
        @RequestParam to: String
    ): ConvertResponse = ConvertResponse(useCase.convert(amount, from, to), to)
}