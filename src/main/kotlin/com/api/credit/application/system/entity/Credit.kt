package com.api.credit.application.system.entity

import com.api.credit.application.system.entity.enumeration.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    var creditCode: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val creditValue: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val dayFirstInstallment: LocalDate,

    @Column(nullable = false)
    val numberOfInstallments: Int = 0,

    @Enumerated
    val status: com.api.credit.application.system.entity.enumeration.Status = com.api.credit.application.system.entity.enumeration.Status.IN_PROGRESS,

    @ManyToOne
    var customer: com.api.credit.application.system.entity.Customer? = null,
)
