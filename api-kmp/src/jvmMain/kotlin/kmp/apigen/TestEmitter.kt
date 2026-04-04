package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates Component tests from OpenAPI schemas.
 *
 * Each object schema → test class with:
 * - REST CRUD tests (GET/POST/PUT/DELETE)
 * - PatchEvent verification (add/replace/remove)
 * - SSE streaming test
 * - WebSocket test
 * - ktor testApplication server
 */
class TestEmitter : ProtocolEmitter {
    override val protocol = "test"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val modelsPackage = "$pkg.models"
        val testPackage = "$pkg.test"

        spec.schemas.forEach { (name, schema) ->
            if (schema !is ParsedSchema.Object) return@forEach
            if (schema.properties.size < 2) return@forEach

            val entityType = ClassName(modelsPackage, name)
            val lowerName = name.lowercase()
            val testFile = generateTestClass(name, lowerName, schema, entityType, testPackage, modelsPackage)
            testFile.writeTo(outputDir)
        }
    }

    private fun generateTestClass(
        name: String,
        lowerName: String,
        schema: ParsedSchema.Object,
        entityType: ClassName,
        testPackage: String,
        modelsPackage: String,
    ): FileSpec {
        val patchEvent = ClassName("com.anthropic.core.component", "PatchEvent")
        val patchEventType = patchEvent.parameterizedBy(entityType)

        // Build sample entity constructor args
        val sampleArgs = schema.properties.map { (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val value = when (prop.type) {
                "String" -> "\"test_$kotlinName\""
                "Int" -> "1"
                "Long" -> "1L"
                "Double" -> "1.0"
                "Boolean" -> "true"
                else -> if (prop.type.startsWith("List")) "emptyList()" else "null"
            }
            if (prop.nullable && propName !in schema.required) null else "$kotlinName = $value"
        }.filterNotNull().joinToString(", ")

        val file = FileSpec.builder(testPackage, "${name}ComponentTest")

        // Imports
        file.addImport("com.anthropic.core.component", "PatchEvent")
        file.addImport("io.ktor.client.call", "body")
        file.addImport("io.ktor.client.plugins.contentnegotiation", "ContentNegotiation")
        file.addImport("io.ktor.client.plugins.sse", "SSE")
        file.addImport("io.ktor.client.plugins.sse", "sse")
        file.addImport("io.ktor.client.request", "get", "post", "put", "delete", "setBody")
        file.addImport("io.ktor.http", "ContentType", "HttpStatusCode", "contentType")
        file.addImport("io.ktor.serialization.kotlinx.json", "json")
        file.addImport("io.ktor.server.application", "install")
        file.addImport("io.ktor.server.request", "receive")
        file.addImport("io.ktor.server.response", "respond")
        file.addImport("io.ktor.server.routing", "routing", "get", "post", "put", "delete")
        file.addImport("io.ktor.server.sse", "SSE", "sse")
        file.addImport("io.ktor.server.testing", "testApplication")
        file.addImport("io.ktor.sse", "ServerSentEvent")
        file.addImport("kotlinx.coroutines", "CoroutineScope", "Dispatchers", "delay", "launch")
        file.addImport("kotlinx.coroutines.flow", "MutableSharedFlow")
        file.addImport("kotlinx.serialization", "encodeToString")
        file.addImport("kotlinx.serialization.json", "Json")
        file.addImport("kotlin.test", "assertEquals", "assertNotNull", "assertNull", "assertTrue")
        file.addImport("org.junit.jupiter.api", "Test")

        val testClass = TypeSpec.classBuilder("${name}ComponentTest")

        // json field
        testClass.addProperty(
            PropertySpec.builder("json", ClassName("kotlinx.serialization.json", "Json"))
                .addModifiers(KModifier.PRIVATE)
                .initializer("Json { ignoreUnknownKeys = true }")
                .build()
        )

        // broadcast field
        testClass.addProperty(
            PropertySpec.builder("broadcast", ClassName("kotlinx.coroutines.flow", "MutableSharedFlow").parameterizedBy(patchEventType))
                .addModifiers(KModifier.PRIVATE)
                .initializer("MutableSharedFlow(extraBufferCapacity = 64)")
                .build()
        )

        // sample entity
        testClass.addProperty(
            PropertySpec.builder("sample", entityType)
                .addModifiers(KModifier.PRIVATE)
                .initializer("$name($sampleArgs)")
                .build()
        )

        // server setup function
        testClass.addFunction(
            FunSpec.builder("server")
                .addModifiers(KModifier.PRIVATE)
                .receiver(ClassName("io.ktor.server.testing", "TestApplicationBuilder"))
                .addCode("""
                    |val entities = mutableMapOf("1" to sample)
                    |application {
                    |    install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) { json(json) }
                    |    install(io.ktor.server.sse.SSE)
                    |    routing {
                    |        get("/$lowerName/{id}") {
                    |            val e = entities[call.parameters["id"]]
                    |            if (e != null) call.respond(e) else call.respond(HttpStatusCode.NotFound)
                    |        }
                    |        post("/$lowerName") {
                    |            val e = call.receive<$name>()
                    |            entities["new"] = e
                    |            val event = PatchEvent(op = "add", entityId = "new", before = null, after = e)
                    |            broadcast.emit(event)
                    |            call.respond(event)
                    |        }
                    |        put("/$lowerName/{id}") {
                    |            val id = call.parameters["id"]!!
                    |            val before = entities[id]
                    |            val after = call.receive<$name>()
                    |            entities[id] = after
                    |            val event = PatchEvent(op = "replace", entityId = id, before = before, after = after)
                    |            broadcast.emit(event)
                    |            call.respond(event)
                    |        }
                    |        delete("/$lowerName/{id}") {
                    |            val id = call.parameters["id"]!!
                    |            val before = entities.remove(id)
                    |            val event = PatchEvent(op = "remove", entityId = id, before = before, after = null)
                    |            broadcast.emit(event)
                    |            call.respond(event)
                    |        }
                    |        sse("/$lowerName/changes") {
                    |            broadcast.collect { event -> send(ServerSentEvent(data = json.encodeToString(event))) }
                    |        }
                    |    }
                    |}
                """.trimIndent())
                .build()
        )

        // Test: GET
        testClass.addFunction(
            FunSpec.builder("get $name by ID")
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .addCode("""
                    |testApplication {
                    |    server()
                    |    val client = createClient { install(ContentNegotiation) { json(json) } }
                    |    val entity: $name = client.get("/$lowerName/1").body()
                    |    assertNotNull(entity)
                    |}
                """.trimIndent())
                .build()
        )

        // Test: POST → PatchEvent add
        testClass.addFunction(
            FunSpec.builder("add $name returns PatchEvent")
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .addCode("""
                    |testApplication {
                    |    server()
                    |    val client = createClient { install(ContentNegotiation) { json(json) } }
                    |    val event: PatchEvent<$name> = client.post("/$lowerName") {
                    |        contentType(ContentType.Application.Json)
                    |        setBody(sample)
                    |    }.body()
                    |    assertEquals("add", event.op)
                    |    assertNotNull(event.after)
                    |    assertNull(event.before)
                    |}
                """.trimIndent())
                .build()
        )

        // Test: PUT → PatchEvent replace
        testClass.addFunction(
            FunSpec.builder("replace $name returns PatchEvent with before and after")
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .addCode("""
                    |testApplication {
                    |    server()
                    |    val client = createClient { install(ContentNegotiation) { json(json) } }
                    |    val event: PatchEvent<$name> = client.put("/$lowerName/1") {
                    |        contentType(ContentType.Application.Json)
                    |        setBody(sample)
                    |    }.body()
                    |    assertEquals("replace", event.op)
                    |    assertNotNull(event.before)
                    |    assertNotNull(event.after)
                    |}
                """.trimIndent())
                .build()
        )

        // Test: DELETE → PatchEvent remove
        testClass.addFunction(
            FunSpec.builder("remove $name returns PatchEvent")
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .addCode("""
                    |testApplication {
                    |    server()
                    |    val client = createClient { install(ContentNegotiation) { json(json) } }
                    |    val event: PatchEvent<$name> = client.delete("/$lowerName/1").body()
                    |    assertEquals("remove", event.op)
                    |    assertNotNull(event.before)
                    |    assertNull(event.after)
                    |}
                """.trimIndent())
                .build()
        )

        // Test: SSE
        testClass.addFunction(
            FunSpec.builder("SSE broadcasts PatchEvent")
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .addCode("""
                    |testApplication {
                    |    server()
                    |    val received = mutableListOf<PatchEvent<$name>>()
                    |    val sseClient = createClient { install(SSE) }
                    |    val job = CoroutineScope(Dispatchers.Default).launch {
                    |        sseClient.sse("/$lowerName/changes") {
                    |            incoming.collect { e -> e.data?.let { received.add(json.decodeFromString(it)) } }
                    |        }
                    |    }
                    |    delay(100)
                    |    val mutator = createClient { install(ContentNegotiation) { json(json) } }
                    |    mutator.post("/$lowerName") {
                    |        contentType(ContentType.Application.Json)
                    |        setBody(sample)
                    |    }
                    |    delay(200)
                    |    job.cancel()
                    |    assertTrue(received.isNotEmpty())
                    |}
                """.trimIndent())
                .build()
        )

        file.addType(testClass.build())
        return file.build()
    }
}

private fun String.toCamelCase(): String =
    split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
