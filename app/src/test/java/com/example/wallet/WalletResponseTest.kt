package com.example.wallet

import com.example.wallet.data.response.WalletResponse
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WalletResponseTest {

    lateinit var people: WalletResponse

    @Before
    fun setup() {
        people = WalletResponse(
            id = 1,
            nombre = "persona",
            pais = "alemania",
            imagenLink = "https://media.revistagq.com/photos/621343d2c789a63cc825a58b/16:9/virgen%20a%20lo%2040.jpeg",
            cuenta = "Cuenta a Plazo",
            saldo = 1000.00,
            depositos = true
        )
    }

    @Test
    fun `testear la creacion de la entidad`() {
        assertEquals(1, people.id)
        assertEquals("persona", people.nombre)
        assertEquals("alemania", people.pais)
        assertEquals("https://media.revistagq.com/photos/621343d2c789a63cc825a58b/16:9/virgen%20a%20lo%2040.jpeg", people.imagenLink)
        assertEquals("Cuenta a Plazo", people.cuenta)
        assertEquals(1000.00, people.saldo, 0.001)
        assertEquals(true, people.depositos)
    }

    @Test
    fun `testear deserealizacion del JSON`() {
        val json = """
           {
                "id": 5,
                "nombre": "Victor Barra",
                "pais": "Peru",
                "imagenLink": "https://media.revistagq.com/photos/621343d2c789a63cc825a58b/16:9/virgen%20a%20lo%2040.jpeg",
                "cuenta": "Cuenta a Plazo",
                "saldo": 4.99999,
                "depositos": true
           }
        """

        val peopleJson = Gson().fromJson(json, WalletResponse::class.java)

        assertEquals(5, peopleJson.id)
        assertEquals("Victor Barra", peopleJson.nombre)
        assertEquals("Peru", peopleJson.pais)
        assertEquals(
            "https://media.revistagq.com/photos/621343d2c789a63cc825a58b/16:9/virgen%20a%20lo%2040.jpeg",
            peopleJson.imagenLink
        )
        assertEquals("Cuenta a Plazo", peopleJson.cuenta)
        assertEquals(4.99999, peopleJson.saldo, 0.001)
        assertEquals(true, peopleJson.depositos)
    }
}
