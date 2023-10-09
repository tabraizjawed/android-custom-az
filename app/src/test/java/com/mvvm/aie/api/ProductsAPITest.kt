package com.mvvm.aie.api

import com.mvvm.aie.repository.ProductsRepository
import com.mvvm.aie.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsAPITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productsAPI: ProductsAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        productsAPI = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductsAPI::class.java)
    }


    @Test
    fun testProducts_Empty() = runTest {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setResponseCode(404))

        val result = ProductsRepository(apiService = productsAPI).getProducts()
        val request = mockWebServer.takeRequest()

        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals(null, result.data)

    }

}