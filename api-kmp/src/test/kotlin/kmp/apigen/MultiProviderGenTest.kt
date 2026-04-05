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

    // === Multipart file upload tests (Suno + PixVerse + ComfyUI + Firefly + NotebookLM) ===

    @Test
    fun `OpenApiParser detects multipart form-data request bodies`() {
        val spec = OpenApiParser.parse(loadSpec("media-upload-openapi.yaml"))

        // Suno audio upload
        val sunoUpload = spec.paths.values.first { it.operationId == "sunoUploadAudio" }
        assertTrue(sunoUpload.multipart, "sunoUploadAudio must be flagged multipart")
        assertTrue("audio" in sunoUpload.multipartFileFields, "audio field must be detected")
        val audioTypes = sunoUpload.multipartFileFields["audio"]!!
        assertTrue(audioTypes.any { it.contains("audio/") }, "audio field must accept audio/* content types: $audioTypes")
        assertTrue("prompt" in sunoUpload.multipartTextFields, "prompt must be a text field")
        assertTrue("model" in sunoUpload.multipartTextFields, "model must be a text field")

        // PixVerse image upload
        val pxUpload = spec.paths.values.first { it.operationId == "pixverseUploadImage" }
        assertTrue(pxUpload.multipart)
        assertTrue("image" in pxUpload.multipartFileFields)
        assertTrue(pxUpload.multipartFileFields["image"]!!.any { it.contains("image/") })

        // PixVerse image-to-video is JSON, not multipart
        val pxGen = spec.paths.values.first { it.operationId == "pixverseImageToVideo" }
        assertFalse(pxGen.multipart, "pixverseImageToVideo uses JSON, not multipart")

        // ComfyUI workflow image upload
        val comfy = spec.paths.values.first { it.operationId == "comfyuiUploadImage" }
        assertTrue(comfy.multipart)
        assertTrue("image" in comfy.multipartFileFields)
        assertEquals(listOf("subfolder", "type", "overwrite"), comfy.multipartTextFields)

        // Adobe Firefly reference upload
        val firefly = spec.paths.values.first { it.operationId == "fireflyUploadReference" }
        assertTrue(firefly.multipart)
        assertTrue("file" in firefly.multipartFileFields)

        // NotebookLM source upload (PDF)
        val nb = spec.paths.values.first { it.operationId == "notebooklmUploadSource" }
        assertTrue(nb.multipart)
        assertTrue("source" in nb.multipartFileFields)
        assertTrue(
            nb.multipartFileFields["source"]!!.any { it.contains("pdf") },
            "NotebookLM source must accept application/pdf",
        )
    }

    @Test
    fun `ServiceGenerator emits ByteArray params for multipart file upload endpoints`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("media-upload-openapi.yaml"))
        val output = tempDir.toFile()

        RestEmitter().emit(spec, "test.media", output)

        // Services are grouped by tag — 5 tags in the spec
        val servicesDir = File(output, "test/media/services")
        assertTrue(servicesDir.exists())
        val serviceFiles = servicesDir.listFiles()?.filter { it.extension == "kt" } ?: emptyList()
        assertTrue(serviceFiles.size >= 5, "expected one service per tag (suno, pixverse, comfyui, firefly, notebooklm)")

        val allCode = serviceFiles.joinToString("\n") { it.readText() }

        // Each file field becomes a ByteArray parameter + a <name>Filename String parameter
        assertTrue(
            allCode.contains("audio: ByteArray") || allCode.contains("audio: kotlin.ByteArray"),
            "audio param must be ByteArray in generated SunoService",
        )
        assertTrue(allCode.contains("audioFilename: String") || allCode.contains("audioFilename: kotlin.String"))
        assertTrue(
            allCode.contains("image: ByteArray") || allCode.contains("image: kotlin.ByteArray"),
            "image param must be ByteArray",
        )
        assertTrue(
            allCode.contains("source: ByteArray") || allCode.contains("source: kotlin.ByteArray"),
            "NotebookLM source upload must generate ByteArray param",
        )
        // 'file' is a Kotlin soft keyword — KotlinPoet escapes it with backticks
        assertTrue(
            Regex("""`?file`?:\s+(kotlin\.)?ByteArray""").containsMatchIn(allCode),
            "Adobe Firefly 'file' field must generate ByteArray param",
        )

        // Text fields alongside file fields get String params
        assertTrue(allCode.contains("prompt: String") || allCode.contains("prompt: kotlin.String"))
        assertTrue(allCode.contains("model: String") || allCode.contains("model: kotlin.String"))
        assertTrue(allCode.contains("subfolder: String") || allCode.contains("subfolder: kotlin.String"))
    }

    @Test
    fun `multipart upload paths do NOT generate a JSON body params parameter`(@TempDir tempDir: Path) {
        val spec = OpenApiParser.parse(loadSpec("media-upload-openapi.yaml"))
        val output = tempDir.toFile()

        RestEmitter().emit(spec, "test.media", output)

        // Find the Suno service file
        val sunoFile = File(output, "test/media/services").walkTopDown()
            .first { it.name.contains("Suno", ignoreCase = true) }
        val sunoCode = sunoFile.readText()

        // A JSON-body operation would have `params: <Ref>` parameter.
        // A multipart operation must NOT have such a `params` parameter for the same op.
        val sunoUploadFn = Regex("""fun sunoUploadAudio\([^)]*\)""").find(sunoCode)?.value ?: ""
        assertFalse(
            sunoUploadFn.contains("params:"),
            "sunoUploadAudio must not have a `params: Model` parameter (it's multipart): $sunoUploadFn",
        )
        assertTrue(
            sunoUploadFn.contains("audio:") && sunoUploadFn.contains("prompt:"),
            "sunoUploadAudio must have file + text field params: $sunoUploadFn",
        )
    }

    @Test
    fun `LlmProvider Suno PixVerse ComfyUi NanoBanana Veo NotebookLM Azure Firefly all available`() {
        // Purely a compile-time + instance check — all providers are reachable from commonMain
        val providers: List<kotlinx.kmp.util.LlmProvider> = listOf(
            kotlinx.kmp.util.LlmProvider.Suno(),
            kotlinx.kmp.util.LlmProvider.PixVerse(),
            kotlinx.kmp.util.LlmProvider.ComfyUi(workflow = ""),
            kotlinx.kmp.util.LlmProvider.GoogleNanoBanana,
            kotlinx.kmp.util.LlmProvider.GoogleVeo(),
            kotlinx.kmp.util.LlmProvider.GoogleNotebookLm(region = "us-central1", projectId = "test"),
            kotlinx.kmp.util.LlmProvider.AzureAi(resourceName = "test-resource", deployment = "gpt-4o"),
            kotlinx.kmp.util.LlmProvider.AdobeFirefly(clientId = "test-client"),
        )
        // Each has a unique non-blank id and base URL
        val ids = providers.map { it.id }.toSet()
        assertEquals(providers.size, ids.size, "each provider must have a unique id")

        // copilotDesigner() returns an AzureAi — verified separately (same id, different config)
        val copilot = kotlinx.kmp.util.LlmProvider.AzureAi.copilotDesigner("test-res")
        assertEquals("azure", copilot.id, "copilotDesigner shares the azure provider id")
        assertTrue(copilot.deployment.contains("dall-e") || copilot.deployment.contains("gpt-image"))
        providers.forEach { p ->
            assertTrue(p.id.isNotBlank(), "${p::class.simpleName} id must be non-blank")
            assertTrue(
                p.baseUrl.isNotBlank() || p.id == "google-notebooklm",  // NotebookLM URL templated on region
                "${p::class.simpleName} baseUrl must be non-blank: ${p.baseUrl}",
            )
        }
        // Specific base URLs
        assertEquals("https://app-api.pixverse.ai/openapi/v2", kotlinx.kmp.util.LlmProvider.PixVerse().baseUrl)
        assertTrue(kotlinx.kmp.util.LlmProvider.Suno().baseUrl.contains("suno"))
        assertEquals(
            "https://generativelanguage.googleapis.com/v1beta",
            kotlinx.kmp.util.LlmProvider.GoogleNanoBanana.baseUrl,
        )
        assertEquals("gemini-2.5-flash-image", kotlinx.kmp.util.LlmProvider.GoogleNanoBanana.defaultModel)
        assertTrue(kotlinx.kmp.util.LlmProvider.GoogleVeo().defaultModel.startsWith("veo-"))
        val azure = kotlinx.kmp.util.LlmProvider.AzureAi(resourceName = "myres", deployment = "dep")
        assertTrue(azure.baseUrl.contains("myres.openai.azure.com"))
        assertTrue(azure.baseUrl.contains("/deployments/dep"))
        val firefly = kotlinx.kmp.util.LlmProvider.AdobeFirefly(clientId = "abc")
        assertEquals("abc", firefly.clientId)
        assertTrue(firefly.baseUrl.contains("firefly-api.adobe.io"))
    }
}
