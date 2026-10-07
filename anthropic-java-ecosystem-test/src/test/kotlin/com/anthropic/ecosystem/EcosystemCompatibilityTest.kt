package com.anthropic.ecosystem

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.BadRequestException
import com.anthropic.models.messages.BashCodeExecutionToolResultBlockParam
import com.anthropic.models.messages.BashCodeExecutionToolResultErrorCode
import com.anthropic.models.messages.BrowserClickTarget
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.net.InetAddress
import java.net.ServerSocket
import java.net.URI
import kotlin.concurrent.thread
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.javaMethod
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class EcosystemCompatibilityTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            // To debug that we're using the right JAR.
            val jarPath = this::class.java.getProtectionDomain().codeSource.location
            println("JAR being used: $jarPath")

            // We have to manually run the test methods instead of using the JUnit runner because it
            // seems impossible to get working with R8.
            val test = EcosystemCompatibilityTest()
            test::class
                .memberFunctions
                .asSequence()
                .filter { function ->
                    function.javaMethod?.isAnnotationPresent(Test::class.java) == true
                }
                .forEach { it.call(test) }
        }
    }

    @Test
    fun proguardRules() {
        assertThat(
                javaClass.classLoader.getResourceAsStream(
                    "META-INF/proguard/anthropic-java-core.pro"
                )
            )
            .isNotNull()

        assertThat(
                javaClass.classLoader.getResourceAsStream(
                    "META-INF/proguard/anthropic-java-client-okhttp.pro"
                )
            )
            .isNotNull()
    }

    @Test
    fun client() {
        val client = AnthropicOkHttpClient.builder().apiKey("my-anthropic-api-key").build()

        assertThat(client).isNotNull()
        assertThat(client.completions()).isNotNull()
        assertThat(client.messages()).isNotNull()
        assertThat(client.models()).isNotNull()
        assertThat(client.files()).isNotNull()
        assertThat(client.skills()).isNotNull()
        assertThat(client.organization()).isNotNull()
        assertThat(client.beta()).isNotNull()
    }

    @Test
    fun request() {
        ServerSocket(0, 1, InetAddress.getLoopbackAddress()).use { server ->
            // Fails every request, so that the test doesn't depend on the response of the method.
            thread(isDaemon = true) {
                server.accept().use { socket ->
                    val input = socket.getInputStream()
                    input.bufferedReader().lineSequence().first { it.isEmpty() }
                    socket
                        .getOutputStream()
                        .write(
                            "HTTP/1.1 400 Bad Request\r\nContent-Length: 2\r\nConnection: close\r\n\r\n{}"
                                .toByteArray()
                        )
                    socket.shutdownOutput()
                    // Closing the socket with the rest of the request unread could reset the
                    // connection.
                    input.readBytes()
                }
            }
            val client =
                AnthropicOkHttpClient.builder()
                    .baseUrl(
                        URI(
                                "http",
                                null,
                                server.inetAddress.hostAddress,
                                server.localPort,
                                null,
                                null,
                                null,
                            )
                            .toString()
                    )
                    .apiKey("my-anthropic-api-key")
                    .build()

            assertThatThrownBy {
                    client
                        .messages()
                        .create(
                            MessageCreateParams.builder()
                                .maxTokens(1024L)
                                .addUserMessage("Hello, world")
                                .model(Model.CLAUDE_OPUS_5)
                                .build()
                        )
                }
                .isInstanceOf(BadRequestException::class.java)

            client.close()
        }
    }

    @Test
    fun jsonValueRoundtrip() {
        val jsonMapper = jsonMapper()
        val jsonValue =
            jsonMapper.readValue(
                "{\"values\":[1,2.5,\"three\",true,null],\"nested\":{\"key\":\"value\"}}",
                jacksonTypeRef<JsonValue>(),
            )

        val roundtrippedJsonValue =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(jsonValue),
                jacksonTypeRef<JsonValue>(),
            )

        assertThat(roundtrippedJsonValue).isEqualTo(jsonValue)
    }

    @Test
    fun bashCodeExecutionToolResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                "{\"content\":{\"error_code\":\"invalid_tool_input\",\"type\":\"bash_code_execution_tool_result_error\"},\"tool_use_id\":\"srvtoolu_SQfNkl1n_JR_\",\"type\":\"bash_code_execution_tool_result\",\"cache_control\":{\"type\":\"ephemeral\",\"ttl\":\"5m\"}}",
                jacksonTypeRef<BashCodeExecutionToolResultBlockParam>(),
            )

        val roundtrippedBashCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultBlockParam),
                jacksonTypeRef<BashCodeExecutionToolResultBlockParam>(),
            )

        bashCodeExecutionToolResultBlockParam.validate()
        assertThat(roundtrippedBashCodeExecutionToolResultBlockParam)
            .isEqualTo(bashCodeExecutionToolResultBlockParam)
    }

    @Test
    fun bashCodeExecutionToolResultBlockParamWithoutOptionalFieldsRoundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                "{\"content\":{\"error_code\":\"invalid_tool_input\",\"type\":\"bash_code_execution_tool_result_error\"},\"tool_use_id\":\"srvtoolu_SQfNkl1n_JR_\",\"type\":\"bash_code_execution_tool_result\"}",
                jacksonTypeRef<BashCodeExecutionToolResultBlockParam>(),
            )

        val roundtrippedBashCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultBlockParam),
                jacksonTypeRef<BashCodeExecutionToolResultBlockParam>(),
            )

        bashCodeExecutionToolResultBlockParam.validate()
        assertThat(bashCodeExecutionToolResultBlockParam._cacheControl().isMissing()).isTrue()
        assertThat(roundtrippedBashCodeExecutionToolResultBlockParam)
            .isEqualTo(bashCodeExecutionToolResultBlockParam)
    }

    @Test
    fun browserClickTargetRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserClickTarget =
            jsonMapper.readValue(
                "{\"type\":\"coordinate\",\"x\":0,\"y\":0}",
                jacksonTypeRef<BrowserClickTarget>(),
            )

        val roundtrippedBrowserClickTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserClickTarget),
                jacksonTypeRef<BrowserClickTarget>(),
            )

        browserClickTarget.validate()
        assertThat(roundtrippedBrowserClickTarget).isEqualTo(browserClickTarget)
    }

    @Test
    fun bashCodeExecutionToolResultErrorCodeRoundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultErrorCode =
            jsonMapper.readValue(
                "\"invalid_tool_input\"",
                jacksonTypeRef<BashCodeExecutionToolResultErrorCode>(),
            )

        val roundtrippedBashCodeExecutionToolResultErrorCode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultErrorCode),
                jacksonTypeRef<BashCodeExecutionToolResultErrorCode>(),
            )

        bashCodeExecutionToolResultErrorCode.validate()
        assertThat(roundtrippedBashCodeExecutionToolResultErrorCode)
            .isEqualTo(bashCodeExecutionToolResultErrorCode)
    }
}
