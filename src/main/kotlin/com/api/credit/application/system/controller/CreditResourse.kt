package com.api.credit.application.system.controller

import com.api.credit.application.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResourse(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: com.api.credit.application.system.controller.dto.CreditDto): ResponseEntity<String> {
        val credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") custumerId: Long): ResponseEntity<List<com.api.credit.application.system.controller.dto.CreditViewList>> {
        val creditViewList = this.creditService.findAllByCustomer(custumerId).stream()
            .map { credit: com.api.credit.application.system.entity.Credit ->
                com.api.credit.application.system.controller.dto.CreditViewList(
                    credit
                )
            }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") custumerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<com.api.credit.application.system.controller.dto.CreditView> {
        val credit = this.creditService.findByCreditCode(custumerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(
            com.api.credit.application.system.controller.dto.CreditView(
                credit
            )
        )
    }
}