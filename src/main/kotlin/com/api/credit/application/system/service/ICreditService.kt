package com.api.credit.application.system.service

import com.api.credit.application.system.entity.Credit
import java.util.UUID

interface ICreditService {

    fun save(credit: com.api.credit.application.system.entity.Credit): com.api.credit.application.system.entity.Credit

    fun findAllByCustomer(customerId: Long): List<com.api.credit.application.system.entity.Credit>

    fun findByCreditCode(customerId: Long, creditCode: UUID): com.api.credit.application.system.entity.Credit
}