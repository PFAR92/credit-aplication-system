package com.api.credit.application.system.service

import com.api.credit.application.system.entity.Address
import com.api.credit.application.system.entity.Credit
import com.api.credit.application.system.entity.Customer
import com.api.credit.application.system.entity.enumeration.Status
import com.api.credit.application.system.repository.CreditRepository
import com.api.credit.application.system.service.impl.CreditService
import com.api.credit.application.system.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
internal class CreditServiceTest {

    @MockK
    lateinit var customerService: CustomerService

    @MockK
    lateinit var creditRepository: CreditRepository

    @InjectMockKs
    lateinit var creditService: CreditService





    @Test
    fun `should create credit`() {
        //given
        val fakeCredit = buildCredit()
        every { customerService.findById(any()) } returns buildCustomer()
        every { creditRepository.save(any()) } returns fakeCredit

        //when
        val actual = creditService.save(fakeCredit)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }

    @Test
    fun `should credit find customer`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCreditList = listOf<Credit>(buildCredit())

        every { customerService.findById(fakeId) } returns buildCustomer()
        every { creditRepository.findAllByCustomer(fakeId) } returns fakeCreditList

        //when
        val actualCreditList = creditService.findAllByCustomer(fakeId)

        //then
        Assertions.assertThat(actualCreditList).isNotNull
        Assertions.assertThat(actualCreditList).isSameAs(fakeCreditList)
        verify (exactly = 1) { creditRepository.findAllByCustomer(fakeId) }
    }

    @Test
    fun `should credit find by credit code`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCredit = buildCredit()
        val fakeCreditCode = buildCredit().creditCode

        //when
        every { customerService.findById(fakeId) } returns buildCustomer()
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit
        val actual = creditRepository.findByCreditCode(fakeCreditCode)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        Assertions.assertThat(actual).isExactlyInstanceOf(Credit::class.java)
        verify (exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }
    }


    private fun buildCredit(
        creditCode: UUID = UUID.randomUUID(),
        creditValue: BigDecimal = BigDecimal.valueOf(5000),
        dayFirstInstallment: LocalDate = LocalDate.of(2025, 5, 26),
        numberOfInstallments: Int = 36,
        status: Status = Status.IN_PROGRESS,
        customer: Customer? = buildCustomer(),
    ) = Credit(
        creditCode = creditCode,
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        status = status,
        customer = customer
    )

    private fun buildCustomer(
        firstName: String = "Paulo",
        lastName: String = "Felipe",
        cpf: String = "28475934625",
        email: String = "paulo@gmail.com",
        password: String = "123456",
        zipCode: String = "38740000",
        street: String = "Rua da Jujuba",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )
}