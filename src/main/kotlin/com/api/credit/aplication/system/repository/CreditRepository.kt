package com.api.credit.aplication.system.repository;

import com.api.credit.aplication.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditRepository : JpaRepository<Credit, Long> {


    fun findByCreditCode(creditCode: UUID): Credit
}