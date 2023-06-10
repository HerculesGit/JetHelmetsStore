package com.herco.jethelmetsstore.domain.usecase

import com.herco.jethelmetsstore.domain.mappers.toDomain
import com.herco.jethelmetsstore.domain.model.Helmet
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetProductUseCaseTest {
    private val mockRepository: IHelmetRepository = mockk()
    private val useCase: GetProductUseCase = GetProductUseCase(mockRepository)

    @Test
    fun `execute should return success with one helmet when repository returns success`() =
        runBlocking {
            // Arrange
            val uuid = "SH001"
            val mockHelmet = Helmet(
                id = uuid,
                brand = "SafetyGuard",
                name = "SafetyGuard Helmet",
                price = 59.99,
                favorite = true,
                details = "The SafetyGuard Helmet provides excellent...",
                sizes = listOf("S", "M", "L", "XL")
            )

            coEvery { mockRepository.getProduct(uuid) } returns Resource.Success(data = mockHelmet)

            // Act
            val result = useCase.execute(uuid)

            // Assert
            Assert.assertTrue(result is Resource.Success)
            assertEquals(mockHelmet, (result as Resource.Success).data.toDomain())

            coVerify(exactly = 1) { mockRepository.getProduct(uuid) }
        }

    @Test
    fun `execute should return error when repository returns error`() =
        runBlocking {
            // Arrange
            val uuid = "SH001"
            val errorMessage = "Failed to fetch helmets"
            coEvery { mockRepository.getProduct(uuid) } returns Resource.Error(errorMessage)

            // Act
            val result = useCase.execute(uuid)

            // Assert
            Assert.assertTrue(result is Resource.Error)
            Assert.assertEquals(errorMessage, (result as Resource.Error).message)

            coVerify(exactly = 1) { mockRepository.getProduct(uuid) }
        }
}