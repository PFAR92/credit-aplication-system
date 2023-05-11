package com.api.credit.aplication.system.repository;

import com.api.credit.aplication.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository : JpaRepository<Credit, Long> {
}