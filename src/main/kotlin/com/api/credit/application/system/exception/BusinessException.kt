package com.api.credit.application.system.exception

data class BusinessException(override val message: String?) : RuntimeException(message)
