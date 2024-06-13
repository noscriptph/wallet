package com.example.wallet.retrofit

import com.example.wallet.data.network.retrofit.RetrofitHelper
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RetrofitHelperTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test Retrofit instance creation`() {
        val baseUrl = mockWebServer.url("/").toString()

        val retrofit = RetrofitHelper.getRetrofit()
        val actualBaseUrl = retrofit.baseUrl().toString()

        // Verificamos que la baseUrl configurada es la esperada
        assertEquals("https://wallet-afuno76sn-talento-projects.vercel.app/", actualBaseUrl)
    }
}
