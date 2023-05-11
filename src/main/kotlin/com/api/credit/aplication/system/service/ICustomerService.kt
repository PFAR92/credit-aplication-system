package com.api.credit.aplication.system.service

import com.api.credit.aplication.system.entity.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)
}