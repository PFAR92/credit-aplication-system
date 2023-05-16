package com.api.credit.aplication.system.service.impl

import com.api.credit.aplication.system.entity.Customer
import com.api.credit.aplication.system.exception.BusinessException
import com.api.credit.aplication.system.repository.CustomerRepository
import com.api.credit.aplication.system.service.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
): ICustomerService {


    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw BusinessException("Id $id not found")
        }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}