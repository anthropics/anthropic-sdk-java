// File generated from our OpenAPI spec by Stainless.

package com.anthropic.ecosystem

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Base64ImageSource
import com.anthropic.models.messages.BashCodeExecutionToolResultErrorCode
import com.anthropic.models.messages.BrowserStateChange
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.javaMethod
import org.assertj.core.api.Assertions.assertThat
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
        val rulesFile =
            javaClass.classLoader.getResourceAsStream("META-INF/proguard/anthropic-java-core.pro")

        assertThat(rulesFile).isNotNull()
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
        assertThat(client.beta()).isNotNull()
    }

    @Test
    fun base64ImageSourceRoundtrip() {
        val jsonMapper = jsonMapper()
        val base64ImageSource =
            Base64ImageSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                .build()

        val roundtrippedBase64ImageSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(base64ImageSource),
                jacksonTypeRef<Base64ImageSource>(),
            )

        assertThat(roundtrippedBase64ImageSource).isEqualTo(base64ImageSource)
    }

    @Test
    fun browserStateChangeRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChange = BrowserStateChange.ofTabOpened("tab_id")

        val roundtrippedBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChange),
                jacksonTypeRef<BrowserStateChange>(),
            )

        assertThat(roundtrippedBrowserStateChange).isEqualTo(browserStateChange)
    }

    @Test
    fun bashCodeExecutionToolResultErrorCodeRoundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultErrorCode =
            BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT

        val roundtrippedBashCodeExecutionToolResultErrorCode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultErrorCode),
                jacksonTypeRef<BashCodeExecutionToolResultErrorCode>(),
            )

        assertThat(roundtrippedBashCodeExecutionToolResultErrorCode)
            .isEqualTo(bashCodeExecutionToolResultErrorCode)
    }
}
