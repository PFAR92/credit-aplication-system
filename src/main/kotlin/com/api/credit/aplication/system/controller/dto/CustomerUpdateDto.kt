package com.api.credit.aplication.system.controller.dto

import com.api.credit.aplication.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(

    @field:NotEmpty(message = "Invalid Input")
    val firstName: String,

    @field:NotEmpty(message = "Invalid Input")
    val lastName: String,

    @field:NotNull(message = "Invalid Input")
    val income: BigDecimal,

    @field:NotEmpty(message = "Invalid Input")
    val zipCode: String,

    @field:NotEmpty(message = "Invalid Input")
    val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = firstName
        customer.lastName = lastName
        customer.income = income
        customer.address.zipCode = zipCode
        customer.address.street = street

        return customer
    }
}
