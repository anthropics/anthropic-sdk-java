package io.swagger.petstore

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.swagger.petstore.models.*
import io.ktor.server.application.*
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PetstoreServerTest {

    private val json = Json { ignoreUnknownKeys = true }
    private val pets = mapOf(
        1L to Pet(id = 1, name = "Fido", category = Category(id = 1, name = "Dogs"), photourls = listOf("http://example.com/fido.jpg"), status = "available"),
        2L to Pet(id = 2, name = "Whiskers", category = Category(id = 2, name = "Cats"), photourls = emptyList(), status = "sold"),
        3L to Pet(id = 3, name = "Rex", photourls = listOf("http://example.com/rex.jpg"), status = "available"),
    )

    @Test
    fun `REST GET pet by ID`() = testApplication {
        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
            routing {
                get("/pet/{id}") {
                    val pet = call.parameters["id"]?.toLongOrNull()?.let { pets[it] }
                    if (pet != null) call.respond(pet) else call.respond(HttpStatusCode.NotFound)
                }
            }
        }
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val pet: Pet = client.get("/pet/1").body()
        assertEquals(1L, pet.id)
        assertEquals("Fido", pet.name)
        assertEquals("Dogs", pet.category?.name)
    }

    @Test
    fun `REST GET pets by status`() = testApplication {
        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
            routing {
                get("/pet/findByStatus") {
                    val status = call.request.queryParameters["status"]
                    call.respond(if (status != null) pets.values.filter { it.status == status } else pets.values.toList())
                }
            }
        }
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val result: List<Pet> = client.get("/pet/findByStatus?status=available").body()
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.status == "available" })
    }

    @Test
    fun `REST POST and GET round-trip`() = testApplication {
        val mutablePets = pets.toMutableMap()
        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
            routing {
                post("/pet") { val p = call.receive<Pet>(); mutablePets[p.id ?: 999] = p; call.respond(p) }
                get("/pet/{id}") { call.respond(mutablePets[call.parameters["id"]?.toLongOrNull()] ?: return@get call.respond(HttpStatusCode.NotFound)) }
            }
        }
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val created: Pet = client.post("/pet") { contentType(ContentType.Application.Json); setBody(Pet(id = 100, name = "TestPet", photourls = emptyList())) }.body()
        assertEquals("TestPet", created.name)
        val fetched: Pet = client.get("/pet/100").body()
        assertEquals("TestPet", fetched.name)
    }

    @Test
    fun `REST GET store inventory`() = testApplication {
        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
            routing {
                get("/store/inventory") { call.respond(pets.values.groupBy { it.status ?: "?" }.mapValues { it.value.size }) }
            }
        }
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val inv: Map<String, Int> = client.get("/store/inventory").body()
        assertTrue(inv.isNotEmpty())
        assertTrue(inv.containsKey("available"))
    }
}
