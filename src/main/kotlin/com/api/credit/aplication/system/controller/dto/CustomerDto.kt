package com.api.credit.aplication.system.controller.dto

import com.api.credit.aplication.system.entity.Address
import com.api.credit.aplication.system.entity.Customer
import java.math.BigDecimal

data class CustomerDto(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipCode: String,
    val street: String
){
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
