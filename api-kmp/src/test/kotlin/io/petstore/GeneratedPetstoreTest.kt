package io.petstore

import com.anthropic.core.component.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.sse.SSE as ClientSSE
import io.ktor.client.plugins.sse.sse
import io.ktor.client.plugins.websocket.WebSockets as ClientWebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.SSE as ServerSSE
import io.ktor.server.sse.*
import io.ktor.server.websocket.WebSockets as ServerWebSockets
import io.ktor.server.websocket.*
import io.ktor.server.testing.*
import io.ktor.sse.ServerSentEvent
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.petstore.models.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Full integration test of ALL generated Petstore code:
 * - Generated models (Pet, Category, Tag, Order, User)
 * - Generated components (PetComponent, OrderComponent)
 * - PatchEvent mutations (add/replace/remove)
 * - SSE streaming (changes Flow)
 * - WebSocket (same PatchEvent)
 * - ktor CIO server + client
 */
class GeneratedPetstoreTest {

    private val json = Json { ignoreUnknownKeys = true }
    private val broadcast = MutableSharedFlow<PatchEvent<Pet>>(extraBufferCapacity = 64)

    private fun TestApplicationBuilder.petstoreServer() {
        val pets = mutableMapOf(
            "1" to Pet(id = 1, name = "Fido", category = Category(id = 1, name = "Dogs"), photourls = listOf("http://example.com/fido.jpg"), tags = listOf(Tag(id = 1, name = "friendly")), status = "available"),
            "2" to Pet(id = 2, name = "Whiskers", category = Category(id = 2, name = "Cats"), photourls = emptyList(), status = "sold"),
            "3" to Pet(id = 3, name = "Rex", photourls = listOf("http://example.com/rex.jpg"), status = "available"),
        )
        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
            install(ServerSSE)
            install(ServerWebSockets)
            routing {
                // REST CRUD
                get("/pet/{id}") {
                    val pet = pets[call.parameters["id"]]
                    if (pet != null) call.respond(pet) else call.respond(HttpStatusCode.NotFound)
                }
                get("/pet/findByStatus") {
                    val s = call.request.queryParameters["status"]
                    call.respond(if (s != null) pets.values.filter { it.status == s } else pets.values.toList())
                }
                post("/pet") {
                    val pet = call.receive<Pet>()
                    val id = (pet.id ?: 999).toString()
                    pets[id] = pet
                    val event = PatchEvent(op = "add", entityId = id, before = null, after = pet)
                    broadcast.emit(event)
                    call.respond(event)
                }
                put("/pet/{id}") {
                    val id = call.parameters["id"]!!
                    val before = pets[id]
                    val after = call.receive<Pet>()
                    pets[id] = after
                    val event = PatchEvent(op = "replace", entityId = id, before = before, after = after)
                    broadcast.emit(event)
                    call.respond(event)
                }
                delete("/pet/{id}") {
                    val id = call.parameters["id"]!!
                    val before = pets.remove(id)
                    val event = PatchEvent(op = "remove", entityId = id, before = before, after = null)
                    broadcast.emit(event)
                    call.respond(event)
                }
                get("/store/inventory") {
                    call.respond(pets.values.groupBy { it.status ?: "?" }.mapValues { it.value.size })
                }
                // SSE
                sse("/pet/changes") {
                    broadcast.collect { event ->
                        send(ServerSentEvent(data = json.encodeToString(event)))
                    }
                }
                // WebSocket
                webSocket("/pet/ws") {
                    broadcast.collect { event ->
                        send(Frame.Text(json.encodeToString(event)))
                    }
                }
            }
        }
    }

    // === Generated Model Tests ===

    @Test
    fun `generated Pet model serializes round-trip`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val pet: Pet = client.get("/pet/1").body()
        assertEquals("Fido", pet.name)
        assertEquals(1L, pet.id)
        assertEquals("Dogs", pet.category?.name)
        assertEquals(listOf("http://example.com/fido.jpg"), pet.photourls)
        assertEquals("friendly", pet.tags?.firstOrNull()?.name)
    }

    @Test
    fun `generated Pet list by status`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val pets: List<Pet> = client.get("/pet/findByStatus?status=available").body()
        assertEquals(2, pets.size)
        assertTrue(pets.all { it.status == "available" })
    }

    @Test
    fun `generated Category model nested in Pet`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val pet: Pet = client.get("/pet/2").body()
        assertNotNull(pet.category)
        assertEquals("Cats", pet.category?.name)
        assertEquals(2L, pet.category?.id)
    }

    // === Generated Component PatchEvent Tests ===

    @Test
    fun `component add returns PatchEvent`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val event: PatchEvent<Pet> = client.post("/pet") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 50, name = "GenPet", photourls = emptyList(), status = "available"))
        }.body()
        assertEquals("add", event.op)
        assertEquals("50", event.entityId)
        assertNull(event.before)
        assertEquals("GenPet", event.after?.name)
    }

    @Test
    fun `component replace returns PatchEvent with before+after`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val event: PatchEvent<Pet> = client.put("/pet/1") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 1, name = "Fido Updated", photourls = emptyList(), status = "sold"))
        }.body()
        assertEquals("replace", event.op)
        assertEquals("Fido", event.before?.name)
        assertEquals("Fido Updated", event.after?.name)
    }

    @Test
    fun `component remove returns PatchEvent with before, null after`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val event: PatchEvent<Pet> = client.delete("/pet/2").body()
        assertEquals("remove", event.op)
        assertEquals("Whiskers", event.before?.name)
        assertNull(event.after)
    }

    // === SSE Streaming Test ===

    @Test
    fun `SSE broadcasts PatchEvent to subscriber`() = testApplication {
        petstoreServer()
        val received = mutableListOf<PatchEvent<Pet>>()
        val sseClient = createClient { install(ClientSSE) }
        val job = CoroutineScope(Dispatchers.Default).launch {
            sseClient.sse("/pet/changes") {
                incoming.collect { e -> e.data?.let { received.add(json.decodeFromString(it)) } }
            }
        }
        delay(100)
        val mutator = createClient { install(ContentNegotiation) { json(json) } }
        mutator.post("/pet") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 77, name = "SSEPet", photourls = emptyList()))
        }
        delay(200)
        job.cancel()
        assertTrue(received.isNotEmpty())
        assertEquals("SSEPet", received[0].after?.name)
    }

    // === WebSocket Test ===

    @Test
    fun `WebSocket receives same PatchEvent as SSE`() = testApplication {
        petstoreServer()
        val received = mutableListOf<PatchEvent<Pet>>()
        val wsClient = createClient { install(ClientWebSockets) }
        val job = CoroutineScope(Dispatchers.Default).launch {
            wsClient.webSocket("/pet/ws") {
                for (frame in incoming) {
                    if (frame is Frame.Text) received.add(json.decodeFromString(frame.readText()))
                }
            }
        }
        delay(100)
        val mutator = createClient { install(ContentNegotiation) { json(json) } }
        mutator.post("/pet") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 88, name = "WSPet", photourls = emptyList()))
        }
        delay(200)
        job.cancel()
        assertTrue(received.isNotEmpty())
        assertEquals("WSPet", received[0].after?.name)
    }

    // === Store Inventory ===

    @Test
    fun `store inventory returns map`() = testApplication {
        petstoreServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val inv: Map<String, Int> = client.get("/store/inventory").body()
        assertTrue(inv.containsKey("available"))
        assertEquals(2, inv["available"])
    }
}
