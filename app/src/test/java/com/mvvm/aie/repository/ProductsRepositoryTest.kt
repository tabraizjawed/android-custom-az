package com.mvvm.aie.repository

import com.mvvm.aie.api.ProductsAPI
import com.mvvm.aie.model.Product
import com.mvvm.aie.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductsRepositoryTest {

    @Mock
    lateinit var productsAPI: ProductsAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun testEmptyList() = runTest {
        Mockito.`when`(productsAPI.getProducts()).thenReturn(Response.success(emptyList()))

        val sut = ProductsRepository(productsAPI)
        val result = sut.getProducts()

        Assert.assertEquals(true, result is NetworkResult.Success)

        Assert.assertEquals(0, result.data!!.size)


    }

    @Test
    fun testGetProducts_expectedProductList() = runTest {
        val productList = listOf<Product>(
           Product(1,"cat","","",1.0,"Prod 1"),
            Product(1,"","","",1.0,""),
            Product(1,"","","",1.0,"")
        )
        Mockito.`when`(productsAPI.getProducts()).thenReturn(Response.success(productList))

        val sut = ProductsRepository(productsAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(3, result.data!!.size)
        Assert.assertEquals("Prod 1", result.data!![0].title)
    }

    @Test
    fun testGetProducts_expectedError() = runTest {
        Mockito.`when`(productsAPI.getProducts()).thenReturn(Response.error(401, "Unauthorized".toResponseBody()))

        val sut = ProductsRepository(productsAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("error loading data", result.message)
    }

}