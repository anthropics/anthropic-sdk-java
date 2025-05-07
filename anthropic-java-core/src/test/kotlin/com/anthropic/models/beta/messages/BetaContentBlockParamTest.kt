// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaContentBlockParamTest {

    @Test
    fun ofText() {
        val text =
            BetaTextBlockParam.builder()
                .text("x")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .addCitation(
                    BetaCitationCharLocationParam.builder()
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofText(text)

        assertThat(betaContentBlockParam.text()).contains(text)
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofText(
                BetaTextBlockParam.builder()
                    .text("x")
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .addCitation(
                        BetaCitationCharLocationParam.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("x")
                            .endCharIndex(0L)
                            .startCharIndex(0L)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofImage() {
        val image =
            BetaImageBlockParam.builder()
                .source(
                    BetaBase64ImageSource.builder()
                        .data("U3RhaW5sZXNzIHJvY2tz")
                        .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                        .build()
                )
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofImage(image)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).contains(image)
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofImageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofImage(
                BetaImageBlockParam.builder()
                    .source(
                        BetaBase64ImageSource.builder()
                            .data("U3RhaW5sZXNzIHJvY2tz")
                            .mediaType(BetaBase64ImageSource.MediaType.IMAGE_JPEG)
                            .build()
                    )
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            BetaToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolUse(toolUse)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).contains(toolUse)
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolUse(
                BetaToolUseBlockParam.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name("x")
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofServerToolUse() {
        val serverToolUse =
            BetaServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofServerToolUse(serverToolUse)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).contains(serverToolUse)
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofServerToolUse(
                BetaServerToolUseBlockParam.builder()
                    .id("srvtoolu_SQfNkl1n_JR_")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofWebSearchToolResult() {
        val webSearchToolResult =
            BetaWebSearchToolResultBlockParam.builder()
                .contentOfItem(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("x")
                            .url("x")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofWebSearchToolResult(webSearchToolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).contains(webSearchToolResult)
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofWebSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofWebSearchToolResult(
                BetaWebSearchToolResultBlockParam.builder()
                    .contentOfItem(
                        listOf(
                            BetaWebSearchResultBlockParam.builder()
                                .encryptedContent("encrypted_content")
                                .title("x")
                                .url("x")
                                .pageAge("page_age")
                                .build()
                        )
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofToolResult() {
        val toolResult =
            BetaToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolResult(toolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).contains(toolResult)
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolResult(
                BetaToolResultBlockParam.builder()
                    .toolUseId("tool_use_id")
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .content("string")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofBase64PdfBlock() {
        val base64PdfBlock =
            BetaBase64PdfBlock.builder()
                .betaBase64PdfSource("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofBase64PdfBlock(base64PdfBlock)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).contains(base64PdfBlock)
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofBase64PdfBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofBase64PdfBlock(
                BetaBase64PdfBlock.builder()
                    .betaBase64PdfSource("U3RhaW5sZXNzIHJvY2tz")
                    .cacheControl(BetaCacheControlEphemeral.builder().build())
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                    .context("x")
                    .title("x")
                    .build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofThinking() {
        val thinking =
            BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()

        val betaContentBlockParam = BetaContentBlockParam.ofThinking(thinking)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).contains(thinking)
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofThinking(
                BetaThinkingBlockParam.builder().signature("signature").thinking("thinking").build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = BetaRedactedThinkingBlockParam.builder().data("data").build()

        val betaContentBlockParam = BetaContentBlockParam.ofRedactedThinking(redactedThinking)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.base64PdfBlock()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).contains(redactedThinking)
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofRedactedThinking(
                BetaRedactedThinkingBlockParam.builder().data("data").build()
            )

        val roundtrippedBetaContentBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockParam),
                jacksonTypeRef<BetaContentBlockParam>(),
            )

        assertThat(roundtrippedBetaContentBlockParam).isEqualTo(betaContentBlockParam)
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
        val betaContentBlockParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaContentBlockParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaContentBlockParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
