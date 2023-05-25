package com.api.credit.application.system.repository;

import com.api.credit.application.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditRepository : JpaRepository<com.api.credit.application.system.entity.Credit, Long> {


    fun findByCreditCode(creditCode: UUID): com.api.credit.application.system.entity.Credit

    @Query(value = "SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCustomer(customerId: Long): List<com.api.credit.application.system.entity.Credit>
}