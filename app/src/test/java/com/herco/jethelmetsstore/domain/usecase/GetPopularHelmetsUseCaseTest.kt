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

class GetPopularHelmetsUseCaseTest {
    private val mockRepository: IHelmetRepository = mockk()
    private val useCase = GetPopularHelmetsUseCase(mockRepository)

    @Test
    fun `execute should return success with list of helmet when repository returns success`() =
        runBlocking {
            // Arrange
            val mockHelmetList = listOf(
                Helmet(
                    id = "SH001",
                    brand = "SafetyGuard",
                    name = "SafetyGuard Helmet",
                    price = 59.99,
                    favorite = true,
                    details = "The SafetyGuard Helmet provides excellent protection and comfort for your head. ",
                    sizes = listOf("S", "M", "L", "XL")
                ),
                Helmet(
                    id = "SH002",
                    brand = "Protecta",
                    name = "Protecta Pro Helmet",
                    price = 79.99,
                    favorite = false,
                    details = "The Protecta Pro Helmet is a durable and reliable choice for head protection.",
                    sizes = listOf("M", "L", "XL")
                ),
                Helmet(
                    id = "SH003",
                    brand = "UltraShield",
                    name = "UltraShield Pro Helmet",
                    price = 99.99,
                    favorite = true,
                    details = "The UltraShield Pro Helmet combines style and safety for a top-notch head protection experience.",
                    sizes = listOf("S", "M", "L")
                ),
                Helmet(
                    id = "SH004",
                    brand = "MaxFit",
                    name = "MaxFit Performance Helmet",
                    price = 69.99,
                    favorite = false,
                    details = "The MaxFit Performance Helmet is designed for athletes and active individuals seeking optimal head protection. ",
                    sizes = listOf("M", "L")
                )
            )

            coEvery { mockRepository.getPopularProducts() } returns Resource.Success(data = mockHelmetList)

            // Act
            val result = useCase.execute()

            // Assert
            Assert.assertTrue(result is Resource.Success)
            assertEquals(mockHelmetList, (result as Resource.Success).data.map {
                it.toDomain()
            })

            coVerify(exactly = 1) { mockRepository.getPopularProducts() }
        }

    @Test
    fun `execute should return error when repository returns error`() =
        runBlocking {
            // Arrange
            val errorMessage = "Failed to fetch popular helmets"
            coEvery { mockRepository.getPopularProducts() } returns Resource.Error(errorMessage)

            // Act
            val result = useCase.execute()

            // Assert
            Assert.assertTrue(result is Resource.Error)
            Assert.assertEquals(errorMessage, (result as Resource.Error).message)

            coVerify(exactly = 1) { mockRepository.getPopularProducts() }
        }
}