package com.api.credit.application.system.service.impl

import com.api.credit.application.system.repository.CustomerRepository
import com.api.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) : ICustomerService {


    override fun save(customer: com.api.credit.application.system.entity.Customer): com.api.credit.application.system.entity.Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): com.api.credit.application.system.entity.Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw com.api.credit.application.system.exception.BusinessException("Id $id not found")
        }

    override fun delete(id: Long) {
        val customer: com.api.credit.application.system.entity.Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}