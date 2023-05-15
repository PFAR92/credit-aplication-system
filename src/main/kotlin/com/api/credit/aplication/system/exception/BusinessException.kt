package com.api.credit.aplication.system.exception

data class BusinessException(override val message: String?) : RuntimeException(message)
