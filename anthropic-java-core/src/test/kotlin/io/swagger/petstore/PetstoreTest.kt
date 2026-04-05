package io.swagger.petstore

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.swagger.petstore.models.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Tests generated Petstore models against WireMock stubs.
 * Verifies that api-gen output deserializes correctly.
 */
@WireMockTest
class PetstoreTest {

    private val json = Json { ignoreUnknownKeys = true }

    private fun client(baseUrl: String) = HttpClient(CIO) {
        install(ContentNegotiation) { json(json) }
    }

    @Test
    fun `GET pet by ID deserializes into generated Pet model`(wmInfo: WireMockRuntimeInfo) = runBlocking {
        stubFor(get(urlEqualTo("/api/v3/pet/1")).willReturn(
            okJson("""{"id":1,"name":"Fido","category":{"id":1,"name":"Dogs"},"photoUrls":["http://example.com/fido.jpg"],"tags":[{"id":1,"name":"cute"}],"status":"available"}""")
        ))

        val pet: Pet = client(wmInfo.httpBaseUrl).get("${wmInfo.httpBaseUrl}/api/v3/pet/1").body()

        assertEquals(1L, pet.id)
        assertEquals("Fido", pet.name)
        assertEquals("Dogs", pet.category?.name)
        assertEquals(listOf("http://example.com/fido.jpg"), pet.photourls)
        assertEquals("available", pet.status)
    }

    @Test
    fun `POST create pet serializes and deserializes`(wmInfo: WireMockRuntimeInfo) = runBlocking {
        stubFor(post(urlEqualTo("/api/v3/pet")).willReturn(
            okJson("""{"id":42,"name":"GeneratedPet","photoUrls":["http://example.com/pet.jpg"],"status":"available"}""")
        ))

        val newPet = Pet(id = 42, name = "GeneratedPet", photourls = listOf("http://example.com/pet.jpg"), status = "available")
        val created: Pet = client(wmInfo.httpBaseUrl).post("${wmInfo.httpBaseUrl}/api/v3/pet") {
            contentType(ContentType.Application.Json)
            setBody(newPet)
        }.body()

        assertEquals(42L, created.id)
        assertEquals("GeneratedPet", created.name)
    }

    @Test
    fun `GET find pets by status returns list`(wmInfo: WireMockRuntimeInfo) = runBlocking {
        stubFor(get(urlPathEqualTo("/api/v3/pet/findByStatus")).willReturn(
            okJson("""[{"id":1,"name":"A","photoUrls":[]},{"id":2,"name":"B","photoUrls":[]}]""")
        ))

        val pets: List<Pet> = client(wmInfo.httpBaseUrl).get("${wmInfo.httpBaseUrl}/api/v3/pet/findByStatus?status=available").body()

        assertEquals(2, pets.size)
        assertEquals("A", pets[0].name)
        assertEquals("B", pets[1].name)
    }

    @Test
    fun `GET store inventory returns map`(wmInfo: WireMockRuntimeInfo) = runBlocking {
        stubFor(get(urlEqualTo("/api/v3/store/inventory")).willReturn(
            okJson("""{"available":10,"pending":3,"sold":5}""")
        ))

        val inventory: Map<String, Int> = client(wmInfo.httpBaseUrl).get("${wmInfo.httpBaseUrl}/api/v3/store/inventory").body()

        assertEquals(10, inventory["available"])
        assertEquals(3, inventory["pending"])
    }

    @Test
    fun `Category model deserializes`(wmInfo: WireMockRuntimeInfo) = runBlocking {
        stubFor(get(urlEqualTo("/api/v3/pet/1")).willReturn(
            okJson("""{"id":1,"name":"Rex","category":{"id":5,"name":"Reptiles"},"photoUrls":[]}""")
        ))

        val pet: Pet = client(wmInfo.httpBaseUrl).get("${wmInfo.httpBaseUrl}/api/v3/pet/1").body()

        assertNotNull(pet.category)
        assertEquals(5L, pet.category?.id)
        assertEquals("Reptiles", pet.category?.name)
    }
}
