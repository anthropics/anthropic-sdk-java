package kotlinx.kmp.util.core.http

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sse.*
import io.ktor.server.request.*
import io.ktor.server.testing.*
import io.ktor.server.websocket.*
import io.ktor.server.websocket.WebSockets
import io.ktor.websocket.*
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerContentNeg

/**
 * Integration tests for ktor CIO server protocols.
 *
 * Tests JSON (de)serialization, SSE streaming, and WebSocket bidirectional
 * messaging via ktor's testApplication host — no real network, no ports.
 */
class KtorServerProtocolTest {

    @Serializable
    data class Pet(
        val id: Long,
        val name: String,
        @SerialName("photo_urls") val photoUrls: List<String> = emptyList(),
        val status: String = "available",
    )

    @Serializable
    data class ErrorResponse(val code: Int, val message: String)

    // --- JSON REST ---

    @Test
    fun `GET JSON returns serialized model`() = testApplication {
        application {
            install(ServerContentNeg) { json(Json { prettyPrint = false }) }
            routing {
                get("/pets/{id}") {
                    val id = call.parameters["id"]!!.toLong()
                    call.respond(Pet(id, "Buddy", listOf("https://example.com/buddy.jpg")))
                }
            }
        }
        val client = createClient { install(ContentNegotiation) { json() } }
        val response = client.get("/pets/42")
        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
        val body = response.bodyAsText()
        assertThat(body).contains("\"id\":42")
        assertThat(body).contains("\"name\":\"Buddy\"")
        assertThat(body).contains("\"photo_urls\"")
    }

    @Test
    fun `POST JSON deserializes request body`() = testApplication {
        application {
            install(ServerContentNeg) { json(Json { ignoreUnknownKeys = true }) }
            routing {
                post("/pets") {
                    val pet = call.receive<Pet>()
                    call.respond(pet.copy(id = 1))
                }
            }
        }
        val client = createClient { install(ContentNegotiation) { json() } }
        val response = client.post("/pets") {
            contentType(ContentType.Application.Json)
            setBody(Pet(0, "Max", status = "pending"))
        }
        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
        val body = response.bodyAsText()
        assertThat(body).contains("\"id\":1")
        assertThat(body).contains("\"name\":\"Max\"")
    }

    @Test
    fun `GET returns 404 for unknown path`() = testApplication {
        application {
            routing { get("/pets") { call.respond(listOf<Pet>()) } }
        }
        val response = client.get("/unknown")
        assertThat(response.status).isEqualTo(HttpStatusCode.NotFound)
    }

    // --- SSE ---

    @Test
    fun `SSE streams events`() = testApplication {
        application {
            install(SSE)
            routing {
                sse("/events") {
                    send(io.ktor.sse.ServerSentEvent(data = """{"id":1,"name":"A"}""", event = "pet"))
                    send(io.ktor.sse.ServerSentEvent(data = """{"id":2,"name":"B"}""", event = "pet"))
                    send(io.ktor.sse.ServerSentEvent(data = "done", event = "complete"))
                }
            }
        }
        val response = client.get("/events") {
            header(HttpHeaders.Accept, "text/event-stream")
        }
        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
        val body = response.bodyAsText()
        assertThat(body).contains("event: pet")
        assertThat(body).contains("""data: {"id":1,"name":"A"}""")
        assertThat(body).contains("""data: {"id":2,"name":"B"}""")
        assertThat(body).contains("event: complete")
    }

    // --- WebSocket ---

    @Test
    fun `WebSocket echo round-trip`() = testApplication {
        application {
            install(WebSockets)
            routing {
                webSocket("/ws/echo") {
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            send(Frame.Text("echo: ${frame.readText()}"))
                        }
                    }
                }
            }
        }
        val client = createClient { install(io.ktor.client.plugins.websocket.WebSockets) }
        client.webSocket("/ws/echo") {
            send(Frame.Text("hello"))
            val response = incoming.receive() as Frame.Text
            assertThat(response.readText()).isEqualTo("echo: hello")

            send(Frame.Text("world"))
            val response2 = incoming.receive() as Frame.Text
            assertThat(response2.readText()).isEqualTo("echo: world")
        }
    }

    @Test
    fun `WebSocket JSON streaming`() = testApplication {
        val json = Json { ignoreUnknownKeys = true }
        application {
            install(WebSockets)
            routing {
                webSocket("/ws/pets") {
                    // Server sends a stream of pets then closes
                    listOf(Pet(1, "A"), Pet(2, "B"), Pet(3, "C")).forEach { pet ->
                        send(Frame.Text(json.encodeToString(Pet.serializer(), pet)))
                    }
                }
            }
        }
        val client = createClient { install(io.ktor.client.plugins.websocket.WebSockets) }
        val pets = mutableListOf<Pet>()
        client.webSocket("/ws/pets") {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    pets.add(json.decodeFromString(Pet.serializer(), frame.readText()))
                }
            }
        }
        assertThat(pets).hasSize(3)
        assertThat(pets.map { it.name }).containsExactly("A", "B", "C")
    }
}
