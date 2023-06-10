package com.herco.jethelmetsstore.domain.usecase

import com.herco.jethelmetsstore.domain.model.Helmet
import com.herco.jethelmetsstore.domain.repository.IHelmetRepository
import com.herco.jethelmetsstore.domain.result.Resource
import com.herco.jethelmetsstore.presentation.model.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UpdateProductUseCaseTest {
    private val mockRepository: IHelmetRepository = mockk()
    private val useCase: UpdateProductUseCase = UpdateProductUseCase(mockRepository)

    @Test
    fun `execute should return success with updated helmet when repository returns success`() =
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
                sizes = listOf("S")
            )

            val mockProduct = Product(
                id = uuid,
                brand = "SafetyGuard",
                name = "SafetyGuard Helmet",
                price = 59.99,
                favorite = true,
                details = "The SafetyGuard Helmet provides excellent...",
                sizes = listOf("S")
            )

            coEvery { mockRepository.updateProduct(mockHelmet) } returns Resource.Success(data = mockHelmet)

            // Act
            val result = useCase.execute(product = mockProduct)

            // Assert
            Assert.assertTrue(result is Resource.Success)
            assertEquals(Unit, (result as Resource.Success).data)

            coVerify(exactly = 1) { mockRepository.updateProduct(mockHelmet) }
        }

    @Test
    fun `execute should return error when repository returns error`() =
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
                sizes = listOf("S")
            )

            val mockProduct = Product(
                id = uuid,
                brand = "SafetyGuard",
                name = "SafetyGuard Helmet",
                price = 59.99,
                favorite = true,
                details = "The SafetyGuard Helmet provides excellent...",
                sizes = listOf("S")
            )

            val errorMessage = "Failed to update helmets"
            coEvery { mockRepository.updateProduct(mockHelmet) } returns Resource.Error(errorMessage)

            // Act
            val result = useCase.execute(mockProduct)

            // Assert
            Assert.assertTrue(result is Resource.Error)
            Assert.assertEquals(errorMessage, (result as Resource.Error).message)

            coVerify(exactly = 1) { mockRepository.updateProduct(mockHelmet) }
        }
}