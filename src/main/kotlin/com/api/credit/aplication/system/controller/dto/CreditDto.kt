package com.api.credit.aplication.system.controller.dto

import com.api.credit.aplication.system.entity.Credit
import com.api.credit.aplication.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayOffInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayOffInstallment,
        numberOfInstallment = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
