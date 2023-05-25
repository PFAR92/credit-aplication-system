package com.api.credit.application.system.service

import com.api.credit.application.system.entity.Customer

interface ICustomerService {

    fun save(customer: com.api.credit.application.system.entity.Customer): com.api.credit.application.system.entity.Customer

    fun findById(id: Long): com.api.credit.application.system.entity.Customer

    fun delete(id: Long)
}