package kmp.apigen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

/**
 * Verifies that api-gen is **vendor-agnostic**: the same generator invocation
 * produces working KMP client + ktor server routes + Apache Camel RouteBuilder
 * + MCP server for two distinct LLM providers (Anthropic Messages + Google
 * Gemini GenerateContent), each with its own `servers[0]` base URL taken
 * directly from the OpenAPI spec.
 *
 * This closes the gap identified in the plan: before this change,
 * `ServiceGenerator.generateClientFactory()` hardcoded `https://api.anthropic.com`
 * as the default baseUrl. After the fix, the default comes from the spec's
 * `servers:` section — so generating against the Google spec produces a client
 * with `https://generativelanguage.googleapis.com/v1beta` as the default,
 * with zero changes to the generator code.
 */
class MultiProviderGenTest {

    private fun loadSpec(resource: String): File {
        // Test resources live under src/test/resources and are on the classpath at runtime
        val url = javaClass.classLoader.getResource(resource)
            ?: error("Spec resource not found: $resource")
        return File(url.toURI())
    }

    @Test
    fun `OpenApiParser captures servers section from Anthropic spec`() {
        val spec = OpenApiParser.parse(loadSpec("anthropic-messages-openapi.yaml"))

        assertTrue(spec.servers.isNotEmpty(), "Anthropic spec must declare servers[]")
        assertEquals("https://api.anthropic.com/v1", spec.servers.first().url)
        assertNotNull(spec.info, "info block must be parsed")
        assertEquals("Anthropic Messages API", spec.info?.title)
    }

    @Test
    fun `OpenApiParser captures servers section from Google Gemini spec`() {
        val spec = OpenApiParser.parse(loadSpec("google-gemini-openapi.yaml"))

        assertTrue(spec.servers.isNotEmpty(), "Google spec must declare servers[]")
        assertEquals("https://generativelanguage.googleapis.com/v1beta", spec.servers.first().url)
        assertEquals("Google Gemini GenerateContent API", spec.info?.title)
    }

    @Test
    fun `RestEmitter uses spec servers0 as default baseUrl — Anthropic`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("anthropic-messages-openapi.yaml"))
        val output = tempDir.toFile()

        RestEmitter().emit(spec, "test.anthropic", output)

        // ServiceGenerator writes the client factory to <basePackage>.client.ClientFactory.kt
        val clientFactory = File(output, "test/anthropic/client/ClientFactory.kt")
        assertTrue(clientFactory.exists(), "ClientFactory.kt must be emitted under client/")
        val factoryText = clientFactory.readText()

        assertTrue(
            factoryText.contains("https://api.anthropic.com/v1"),
            "Generated client factory must use Anthropic servers[0] URL.\nGot: $factoryText",
        )
    }

    @Test
    fun `RestEmitter uses spec servers0 as default baseUrl — Google Gemini`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("google-gemini-openapi.yaml"))
        val output = tempDir.toFile()

        RestEmitter().emit(spec, "test.google", output)

        val clientFactory = File(output, "test/google/client/ClientFactory.kt")
        assertTrue(clientFactory.exists(), "ClientFactory.kt must be emitted for Google spec")
        val factoryText = clientFactory.readText()

        assertTrue(
            factoryText.contains("https://generativelanguage.googleapis.com/v1beta"),
            "Generated client for Google spec must use Gemini servers[0] URL, not Anthropic's.\nGot: $factoryText",
        )
        assertFalse(
            factoryText.contains("api.anthropic.com"),
            "Generated client for Google spec MUST NOT contain Anthropic URL",
        )
    }

    @Test
    fun `ServerRouteEmitter emits ktor routing for Anthropic spec`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("anthropic-messages-openapi.yaml"))
        val output = tempDir.toFile()

        ServerRouteEmitter().emit(spec, "test.anthropic", output)

        val routes = File(output, "test/anthropic/server")
        assertTrue(routes.exists(), "server/ directory must be created")
        val messagesRoutes = routes.listFiles()?.firstOrNull { it.name.contains("Messages") }
        assertNotNull(messagesRoutes, "expected MessagesRoutes.kt")
        val text = messagesRoutes!!.readText()
        assertTrue(text.contains("fun Routing.messagesRoutes"), "must emit Routing extension fn")
        assertTrue(text.contains("LlmProviderClient"), "handler must take LlmProviderClient")
        assertTrue(text.contains("post(\"/messages\")"), "must include POST /messages route")

        val appFile = File(routes, "GeneratedApplication.kt")
        assertTrue(appFile.exists(), "GeneratedApplication.kt must be emitted")
        val appText = appFile.readText()
        assertTrue(appText.contains("install(ContentNegotiation)"))
        assertTrue(appText.contains("install(SSE)"))
        assertTrue(appText.contains("generatedModule"))
    }

    @Test
    fun `ServerRouteEmitter emits ktor routing for Google Gemini spec`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("google-gemini-openapi.yaml"))
        val output = tempDir.toFile()

        ServerRouteEmitter().emit(spec, "test.google", output)

        val routes = File(output, "test/google/server")
        assertTrue(routes.exists())
        val modelsRoutes = routes.listFiles()?.firstOrNull { it.name.contains("Models") }
        assertNotNull(modelsRoutes, "expected ModelsRoutes.kt for Gemini")
        val text = modelsRoutes!!.readText()
        assertTrue(text.contains("fun Routing.modelsRoutes"))
        assertTrue(
            text.contains("generateContent") || text.contains("/models"),
            "must include Gemini operation path",
        )
    }

    @Test
    fun `CamelRouteEmitter emits vendor-agnostic RouteBuilder for Anthropic`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("anthropic-messages-openapi.yaml"))
        val output = tempDir.toFile()

        CamelRouteEmitter().emit(spec, "test.anthropic", output)

        val camelDir = File(output, "test/anthropic/camel")
        assertTrue(camelDir.exists(), "camel/ dir must be created")
        val builder = camelDir.listFiles()?.firstOrNull { it.name.endsWith("RouteBuilder.kt") }
        assertNotNull(builder, "expected a RouteBuilder.kt")
        val text = builder!!.readText()
        assertTrue(text.contains("class MessagesRouteBuilder"))
        assertTrue(text.contains("org.apache.camel.builder.RouteBuilder"))
        assertTrue(
            text.contains("llm:") && text.contains("{{llm.provider}}"),
            "must use vendor-agnostic llm: component with provider property",
        )
        assertFalse(
            text.contains("anthropic:messages"),
            "must NOT hardcode 'anthropic:' URI — must use generic 'llm:' scheme",
        )

        val properties = File(camelDir, "llm-providers.properties")
        assertTrue(properties.exists(), "llm-providers.properties must be emitted")
        val propText = properties.readText()
        assertTrue(propText.contains("llm.provider=anthropic"))
        assertTrue(propText.contains("# llm.provider=google"))
        assertTrue(propText.contains("# llm.provider=lmstudio"))
        assertTrue(propText.contains("# llm.provider=comfyui"))
        assertTrue(propText.contains("# llm.provider=jlama"))
        assertTrue(propText.contains("# llm.provider=langchain4j:"))
    }

    @Test
    fun `CamelRouteEmitter emits RouteBuilder for Google Gemini with llm component`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("google-gemini-openapi.yaml"))
        val output = tempDir.toFile()

        CamelRouteEmitter().emit(spec, "test.google", output)

        val builder = File(output, "test/google/camel").walkTopDown()
            .firstOrNull { it.name.endsWith("RouteBuilder.kt") }
        assertNotNull(builder)
        val text = builder!!.readText()
        assertTrue(
            text.contains("llm:") && text.contains("{{llm.provider}}"),
            "Gemini-derived routes must ALSO use generic llm: component",
        )
        assertFalse(
            text.contains("google:") || text.contains("gemini:"),
            "must NOT introduce a google: or gemini: Camel component",
        )
    }

    @Test
    fun `McpEmitter emits runnable Kotlin McpServer + McpClient alongside tools_json`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("anthropic-messages-openapi.yaml"))
        val output = tempDir.toFile()

        McpEmitter().emit(spec, "test.anthropic", output)

        // Static tools manifest (existing behavior)
        val toolsJson = File(output, "mcp/tools.json")
        assertTrue(toolsJson.exists(), "tools.json must be emitted")
        val toolsText = toolsJson.readText()
        assertTrue(toolsText.contains("createMessage"), "tools.json must include createMessage tool")

        // NEW: runnable McpServer + McpClient Kotlin files
        val mcpPkg = File(output, "test/anthropic/mcp")
        assertTrue(mcpPkg.exists(), "generated mcp/ package dir must exist")
        val serverFile = mcpPkg.listFiles()?.firstOrNull { it.name.endsWith("McpServer.kt") }
        assertNotNull(serverFile, "McpServer.kt must be emitted")
        val serverText = serverFile!!.readText()
        assertTrue(serverText.contains("import io.modelcontextprotocol.kotlin.sdk.server.Server"))
        assertTrue(serverText.contains("server.addTool("))
        assertTrue(serverText.contains("createMessage"), "all POST paths must be registered as tools")
        assertTrue(serverText.contains("LlmProviderClient"), "server must delegate to LlmProviderClient")

        val clientFile = mcpPkg.listFiles()?.firstOrNull { it.name.endsWith("McpClient.kt") }
        assertNotNull(clientFile, "McpClient.kt must be emitted")
        assertTrue(clientFile!!.readText().contains("io.modelcontextprotocol.kotlin.sdk.client.Client"))
    }

    @Test
    fun `all emitters run together end-to-end for both Anthropic and Google specs`(@TempDir tempDir: Path) {
        listOf(
            "anthropic-messages-openapi.yaml" to "gen.anthropic",
            "google-gemini-openapi.yaml" to "gen.google",
        ).forEach { (resource, pkg) ->
            val spec = OpenApiParser.parse(loadSpec(resource))
            val output = File(tempDir.toFile(), resource.substringBefore("."))

            // Run every emitter that's stable + doesn't need extra fixtures
            RestEmitter().emit(spec, pkg, output)
            ServerRouteEmitter().emit(spec, pkg, output)
            CamelRouteEmitter().emit(spec, pkg, output)
            McpEmitter().emit(spec, pkg, output)

            // All produced at least some files
            val generated = output.walkTopDown().filter { it.isFile }.toList()
            assertTrue(generated.isNotEmpty(), "no files generated for $resource")

            // Assert the provider's unique base URL appears somewhere in the output
            val allText = generated.filter { it.extension == "kt" || it.extension == "json" || it.extension == "properties" }
                .joinToString("\n") { it.readText() }
            val expectedHost = if (resource.contains("anthropic")) "api.anthropic.com"
                else "generativelanguage.googleapis.com"
            assertTrue(
                allText.contains(expectedHost),
                "Expected generated output for $resource to reference $expectedHost",
            )
        }
    }
}
