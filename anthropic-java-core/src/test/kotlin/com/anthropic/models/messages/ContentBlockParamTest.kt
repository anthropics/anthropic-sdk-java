// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ContentBlockParamTest {

    @Test
    fun ofText() {
        val text =
            TextBlockParam.builder()
                .text("x")
                .cacheControl(CacheControlEphemeral.builder().build())
                .addCitation(
                    CitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofText(text)

        assertThat(contentBlockParam.text()).contains(text)
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofText(
                TextBlockParam.builder()
                    .text("x")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .addCitation(
                        CitationCharLocationParam.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofImage() {
        val image =
            ImageBlockParam.builder()
                .source(
                    Base64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofImage(image)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).contains(image)
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofImageRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofImage(
                ImageBlockParam.builder()
                    .source(
                        Base64ImageSource.builder()
                            .data("U3RhaW5sZXNzIHJvY2tz")
                            .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                            .build()
                    )
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofDocument() {
        val document =
            DocumentBlockParam.builder()
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(CacheControlEphemeral.builder().build())
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        val contentBlockParam = ContentBlockParam.ofDocument(document)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).contains(document)
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofDocumentRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofDocument(
                DocumentBlockParam.builder()
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .citations(CitationsConfigParam.builder().enabled(true).build())
                    .context("x")
                    .title("x")
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofThinking() {
        val thinking =
            ThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        val contentBlockParam = ContentBlockParam.ofThinking(thinking)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).contains(thinking)
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofThinking(
                ThinkingBlockParam.builder().signature("signature").thinking("thinking").build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = RedactedThinkingBlockParam.builder().data("data").build()

        val contentBlockParam = ContentBlockParam.ofRedactedThinking(redactedThinking)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).contains(redactedThinking)
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofRedactedThinking(
                RedactedThinkingBlockParam.builder().data("data").build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            ToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofToolUse(toolUse)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).contains(toolUse)
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofToolUse(
                ToolUseBlockParam.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name("x")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofToolResult() {
        val toolResult =
            ToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(CacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()

        val contentBlockParam = ContentBlockParam.ofToolResult(toolResult)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).contains(toolResult)
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofToolResult(
                ToolResultBlockParam.builder()
                    .toolUseId("tool_use_id")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .content("string")
                    .isError(true)
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofServerToolUse() {
        val serverToolUse =
            ServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofServerToolUse(serverToolUse)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).contains(serverToolUse)
        assertThat(contentBlockParam.webSearchToolResult()).isEmpty
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofServerToolUse(
                ServerToolUseBlockParam.builder()
                    .id("srvtoolu_SQfNkl1n_JR_")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    @Test
    fun ofWebSearchToolResult() {
        val webSearchToolResult =
            WebSearchToolResultBlockParam.builder()
                .contentOfItem(
                    listOf(
                        WebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(CacheControlEphemeral.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofWebSearchToolResult(webSearchToolResult)

        assertThat(contentBlockParam.text()).isEmpty
        assertThat(contentBlockParam.image()).isEmpty
        assertThat(contentBlockParam.document()).isEmpty
        assertThat(contentBlockParam.thinking()).isEmpty
        assertThat(contentBlockParam.redactedThinking()).isEmpty
        assertThat(contentBlockParam.toolUse()).isEmpty
        assertThat(contentBlockParam.toolResult()).isEmpty
        assertThat(contentBlockParam.serverToolUse()).isEmpty
        assertThat(contentBlockParam.webSearchToolResult()).contains(webSearchToolResult)
    }

    @Test
    fun ofWebSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofWebSearchToolResult(
                WebSearchToolResultBlockParam.builder()
                    .contentOfItem(
                        listOf(
                            WebSearchResultBlockParam.builder()
                                .encryptedContent("encrypted_content")
                                .title("title")
                                .url("url")
                                .pageAge("page_age")
                                .build()
                        )
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .cacheControl(CacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlockParam),
                jacksonTypeRef<ContentBlockParam>(),
            )

        assertThat(roundtrippedContentBlockParam).isEqualTo(contentBlockParam)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val contentBlockParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ContentBlockParam>())

        val e = assertThrows<AnthropicInvalidDataException> { contentBlockParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
