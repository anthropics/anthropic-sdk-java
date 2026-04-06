package kmp.apigen

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.io.TempDir
import org.assertj.core.api.Assertions.assertThat
import java.io.File
import java.nio.file.Path

/**
 * End-to-end test: parse the full Anthropic OpenAPI spec and generate
 * KMP-native models + services + MCP tools.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnthropicApiGenTest {

    private lateinit var spec: ParsedSpec

    @BeforeAll
    fun parseSpec() {
        val specFile = File(javaClass.classLoader.getResource("anthropic-full-openapi.yaml")!!.toURI())
        spec = OpenApiParser.parse(specFile)
        println("Parsed: ${spec.schemas.size} schemas, ${spec.paths.size} paths, ${spec.servers.size} servers")
    }

    @Test
    fun `parse full Anthropic OpenAPI spec`() {
        assertThat(spec.schemas).isNotEmpty()
        assertThat(spec.paths).isNotEmpty()
        assertThat(spec.servers).isNotEmpty()
        assertThat(spec.servers[0].url).contains("anthropic")
    }

    @Test
    fun `generate KMP models from full Anthropic spec`(@TempDir tempDir: Path) {
        val output = tempDir.toFile()
        val modelGen = ModelGenerator("com.anthropic.api", output)
        var modelCount = 0
        var failCount = 0
        spec.schemas.forEach { (name, schema) ->
            try {
                modelGen.generate(name, schema)
                modelCount++
            } catch (e: Exception) {
                failCount++
            }
        }

        println("Generated $modelCount models, $failCount failures from ${spec.schemas.size} schemas")
        assertThat(modelCount).isGreaterThan(0)
        assertThat(failCount).isLessThan(spec.schemas.size / 20) // <5% failure rate

        val modelFiles = output.walkTopDown().filter { it.extension == "kt" }.toList()
        assertThat(modelFiles).isNotEmpty()
        assertThat(modelFiles.first().readText()).contains("@Serializable")
        assertThat(modelFiles.any { it.readText().contains("@JsonProperty") }).isTrue()
    }

    @Test
    fun `generate services from full Anthropic spec`(@TempDir tempDir: Path) {
        val output = tempDir.toFile()
        ServiceGenerator("com.anthropic.api", output).generate(spec.paths, spec.securitySchemes)

        val serviceFiles = output.walkTopDown()
            .filter { it.extension == "kt" && it.name.contains("Service") }
            .toList()
        assertThat(serviceFiles).isNotEmpty()
        assertThat(serviceFiles.first().readText()).contains("suspend fun")
    }

    @Test
    fun `generate MCP tools from full Anthropic spec`(@TempDir tempDir: Path) {
        val output = tempDir.toFile()
        McpEmitter().emit(spec, "com.anthropic.api", output)

        val toolsJson = File(output, "mcp/tools.json")
        assertThat(toolsJson).exists()
        assertThat(toolsJson.readText()).contains("messages_post")

        val serverFiles = output.walkTopDown().filter { it.name.contains("McpServer") }.toList()
        assertThat(serverFiles).isNotEmpty()
    }
}
