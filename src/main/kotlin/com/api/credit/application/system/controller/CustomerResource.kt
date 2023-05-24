package com.api.credit.application.system.controller

import com.api.credit.application.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: com.api.credit.application.system.controller.dto.CustomerDto): ResponseEntity<com.api.credit.application.system.controller.dto.CustomerView> {
        val saveCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(
            com.api.credit.application.system.controller.dto.CustomerView(
                saveCustomer
            )
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<com.api.credit.application.system.controller.dto.CustomerView> {
        val customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(
            com.api.credit.application.system.controller.dto.CustomerView(
                customer
            )
        )
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) =
        this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: com.api.credit.application.system.controller.dto.CustomerUpdateDto
    ): ResponseEntity<com.api.credit.application.system.controller.dto.CustomerView> {

        val customer = this.customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdate = this.customerService.save(customerToUpdate)

        return ResponseEntity.status(HttpStatus.OK).body(
            com.api.credit.application.system.controller.dto.CustomerView(
                customerUpdate
            )
        )
    }
}