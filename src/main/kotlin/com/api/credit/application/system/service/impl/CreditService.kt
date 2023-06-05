package com.api.credit.application.system.service.impl

import com.api.credit.application.system.entity.Credit
import com.api.credit.application.system.repository.CreditRepository
import com.api.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val customerService: CustomerService,
    private val creditRepository: CreditRepository
) : ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<com.api.credit.application.system.entity.Credit> =
        this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): com.api.credit.application.system.entity.Credit {
        val credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw IllegalArgumentException("CreditCode $creditCode not found"))
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact admin")
    }
}