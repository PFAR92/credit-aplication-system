package com.api.credit.application.system.controller.dto

import com.api.credit.application.system.entity.Credit
import com.api.credit.application.system.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(

    @field:NotNull(message = "Invalid Input")
    val creditValue: BigDecimal,

    @field:Future(message = "Future date only")
    val dayOffInstallment: LocalDate,

    @field:NotNull(message = "Invalid Input")
    @field:Max(value = 48, message = "number max = 48")
    @field:Positive(message = "Invalid Input")
    val numberOfInstallments: Int,

    @field:NotNull(message = "Invalid Input")
    val customerId: Long
) {
    fun toEntity(): com.api.credit.application.system.entity.Credit = com.api.credit.application.system.entity.Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayOffInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = com.api.credit.application.system.entity.Customer(id = this.customerId)
    )
}
