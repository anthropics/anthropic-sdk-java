// File generated from our OpenAPI spec by Stainless.

package com.anthropic.proguard

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Base64ImageSource
import com.anthropic.models.messages.CitationCharLocation
import com.anthropic.models.messages.ContentBlock
import com.anthropic.models.messages.StopReason
import com.anthropic.models.messages.TextBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class ProGuardCompatibilityTest {

    companion object {

        @BeforeAll
        @JvmStatic
        fun setUp() {
            // To debug that we're using the right JAR.
            val jarPath = this::class.java.getProtectionDomain().codeSource.location
            println("JAR being used: $jarPath")
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
    fun contentBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofText(
                TextBlock.builder()
                    .addCitation(
                        CitationCharLocation.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("document_title")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .text("text")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun stopReasonRoundtrip() {
        val jsonMapper = jsonMapper()
        val stopReason = StopReason.END_TURN

        val roundtrippedStopReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(stopReason),
                jacksonTypeRef<StopReason>(),
            )

        assertThat(roundtrippedStopReason).isEqualTo(stopReason)
    }
}
