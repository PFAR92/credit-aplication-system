package com.api.credit.aplication.system.controller

import com.api.credit.aplication.system.controller.dto.CreditDto
import com.api.credit.aplication.system.controller.dto.CreditViewList
import com.api.credit.aplication.system.entity.Credit
import com.api.credit.aplication.system.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResourse(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String {
        val credit = this.creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") custumerId: Long): List<CreditViewList> {
        return this.creditService.findAllByCustomer(custumerId).stream()
            .map { credit: Credit -> CreditViewList(credit)}.collect(Collectors.toList())
    }
}