package com.api.credit.aplication.system.entity

import jakarta.persistence.Column

data class Address(
    @Column(nullable = false)
    var zipCode: String = "",

    @Column(nullable = false)
    var street: String = ""
)