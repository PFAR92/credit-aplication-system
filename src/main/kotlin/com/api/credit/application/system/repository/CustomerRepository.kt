package com.api.credit.application.system.repository;

import com.api.credit.application.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<com.api.credit.application.system.entity.Customer, Long> {
}