package io.swagger.petstore

import com.anthropic.core.component.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.sse.SSE as ClientSSE
import io.ktor.client.plugins.sse.sse
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.SSE as ServerSSE
import io.ktor.server.sse.*
import io.ktor.server.testing.*
import io.ktor.sse.ServerSentEvent
import io.swagger.petstore.models.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ComponentTest {

    private val json = Json { ignoreUnknownKeys = true }
    private val broadcast = MutableSharedFlow<PatchEvent<Pet>>(extraBufferCapacity = 64)

    private fun TestApplicationBuilder.petComponentServer() {
        val pets = mutableMapOf(
            "1" to Pet(id = 1, name = "Fido", photourls = emptyList(), status = "available"),
            "2" to Pet(id = 2, name = "Whiskers", photourls = emptyList(), status = "sold"),
        )
        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
            install(ServerSSE)
            routing {
                get("/pet/{id}") {
                    val pet = pets[call.parameters["id"]]
                    if (pet != null) call.respond(pet) else call.respond(HttpStatusCode.NotFound)
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
                sse("/pet/changes") {
                    broadcast.collect { event ->
                        send(ServerSentEvent(data = json.encodeToString(event)))
                    }
                }
            }
        }
    }

    @Test
    fun `add returns PatchEvent with op=add`() = testApplication {
        petComponentServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val event: PatchEvent<Pet> = client.post("/pet") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 42, name = "NewPet", photourls = emptyList()))
        }.body()

        assertEquals("add", event.op)
        assertEquals("42", event.entityId)
        assertNull(event.before)
        assertEquals("NewPet", event.after!!.name)
    }

    @Test
    fun `replace returns PatchEvent with before and after`() = testApplication {
        petComponentServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val event: PatchEvent<Pet> = client.put("/pet/1") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 1, name = "Fido Updated", photourls = emptyList(), status = "sold"))
        }.body()

        assertEquals("replace", event.op)
        assertEquals("Fido", event.before!!.name)
        assertEquals("Fido Updated", event.after!!.name)
    }

    @Test
    fun `remove returns PatchEvent with before, null after`() = testApplication {
        petComponentServer()
        val client = createClient { install(ContentNegotiation) { json(json) } }
        val event: PatchEvent<Pet> = client.delete("/pet/2").body()

        assertEquals("remove", event.op)
        assertEquals("Whiskers", event.before!!.name)
        assertNull(event.after)
    }

    @Test
    fun `SSE broadcasts PatchEvent to subscriber`() = testApplication {
        petComponentServer()
        val received = mutableListOf<PatchEvent<Pet>>()

        // Subscribe via SSE
        val sseClient = createClient { install(ClientSSE) }
        val job = CoroutineScope(Dispatchers.Default).launch {
            sseClient.sse("/pet/changes") {
                incoming.collect { event ->
                    event.data?.let { received.add(json.decodeFromString(it)) }
                }
            }
        }
        delay(100)

        // Mutate
        val mutator = createClient { install(ContentNegotiation) { json(json) } }
        mutator.post("/pet") {
            contentType(ContentType.Application.Json)
            setBody(Pet(id = 77, name = "SSEPet", photourls = emptyList()))
        }
        delay(200)
        job.cancel()

        assertTrue(received.isNotEmpty(), "SSE subscriber should receive PatchEvent")
        assertEquals("add", received[0].op)
        assertEquals("SSEPet", received[0].after!!.name)
    }
}
