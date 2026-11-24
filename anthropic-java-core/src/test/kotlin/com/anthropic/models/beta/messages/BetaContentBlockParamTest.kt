package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .addCitation(
                    BetaCitationCharLocationParam.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofText(text)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("text" to Optional.of(text)))
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofText(
                BetaTextBlockParam.builder()
                    .text("x")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .addCitation(
                        BetaCitationCharLocationParam.builder()
                            .citedText("The grass is green. The sky is blue.")
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
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .transformations(
                    BetaImageTransformationsParam.builder()
                        .oversizedImage(BetaImageTransformationsParam.OversizedImage.DOWNSIZE)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofImage(image)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("image" to Optional.of(image)))
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
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .transformations(
                        BetaImageTransformationsParam.builder()
                            .oversizedImage(BetaImageTransformationsParam.OversizedImage.DOWNSIZE)
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
    fun ofDocument() {
        val document =
            BetaRequestDocumentBlock.builder()
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofDocument(document)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("document" to Optional.of(document)))
    }

    @Test
    fun ofDocumentRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofDocument(
                BetaRequestDocumentBlock.builder()
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
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
    fun ofSearchResult() {
        val searchResult =
            BetaSearchResultBlockParam.builder()
                .addContent(
                    BetaTextBlockParam.builder()
                        .text("x")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .addCitation(
                            BetaCitationCharLocationParam.builder()
                                .citedText("The grass is green. The sky is blue.")
                                .documentIndex(0L)
                                .documentTitle("x")
                                .endCharIndex(0L)
                                .startCharIndex(0L)
                                .build()
                        )
                        .build()
                )
                .source("source")
                .title("title")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofSearchResult(searchResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("searchResult" to Optional.of(searchResult)))
    }

    @Test
    fun ofSearchResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofSearchResult(
                BetaSearchResultBlockParam.builder()
                    .addContent(
                        BetaTextBlockParam.builder()
                            .text("x")
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .addCitation(
                                BetaCitationCharLocationParam.builder()
                                    .citedText("The grass is green. The sky is blue.")
                                    .documentIndex(0L)
                                    .documentTitle("x")
                                    .endCharIndex(0L)
                                    .startCharIndex(0L)
                                    .build()
                            )
                            .build()
                    )
                    .source("source")
                    .title("title")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(BetaCitationsConfigParam.builder().enabled(true).build())
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

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("thinking" to Optional.of(thinking)))
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
        val redactedThinking = BetaRedactedThinkingBlockParam.of("data")

        val betaContentBlockParam = BetaContentBlockParam.ofRedactedThinking(redactedThinking)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("redactedThinking" to Optional.of(redactedThinking)))
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofRedactedThinking(BetaRedactedThinkingBlockParam.of("data"))

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
                .input(
                    BetaToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .toolsetName("toolset_name")
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolUse(toolUse)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("toolUse" to Optional.of(toolUse)))
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolUse(
                BetaToolUseBlockParam.builder()
                    .id("id")
                    .input(
                        BetaToolUseBlockParam.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("x")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
                    .toolsetName("toolset_name")
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
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("string")
                .isError(true)
                .toolsetName("toolset_name")
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolResult(toolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("toolResult" to Optional.of(toolResult)))
    }

    @Test
    fun ofToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolResult(
                BetaToolResultBlockParam.builder()
                    .toolUseId("tool_use_id")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .content("string")
                    .isError(true)
                    .toolsetName("toolset_name")
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
                .id("srvtoolu_c")
                .input(
                    BetaServerToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(BetaServerToolUseBlockParam.Name.ADVISOR)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofServerToolUse(serverToolUse)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("serverToolUse" to Optional.of(serverToolUse)))
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofServerToolUse(
                BetaServerToolUseBlockParam.builder()
                    .id("srvtoolu_c")
                    .input(
                        BetaServerToolUseBlockParam.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name(BetaServerToolUseBlockParam.Name.ADVISOR)
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
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
                .contentOfResultBlock(
                    listOf(
                        BetaWebSearchResultBlockParam.builder()
                            .encryptedContent("encrypted_content")
                            .title("title")
                            .url("url")
                            .pageAge("page_age")
                            .build()
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofWebSearchToolResult(webSearchToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("webSearchToolResult" to Optional.of(webSearchToolResult)))
    }

    @Test
    fun ofWebSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofWebSearchToolResult(
                BetaWebSearchToolResultBlockParam.builder()
                    .contentOfResultBlock(
                        listOf(
                            BetaWebSearchResultBlockParam.builder()
                                .encryptedContent("encrypted_content")
                                .title("title")
                                .url("url")
                                .pageAge("page_age")
                                .build()
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
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
    fun ofWebFetchToolResult() {
        val webFetchToolResult =
            BetaWebFetchToolResultBlockParam.builder()
                .content(
                    BetaWebFetchToolResultErrorBlockParam.of(
                        BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofWebFetchToolResult(webFetchToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("webFetchToolResult" to Optional.of(webFetchToolResult)))
    }

    @Test
    fun ofWebFetchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofWebFetchToolResult(
                BetaWebFetchToolResultBlockParam.builder()
                    .content(
                        BetaWebFetchToolResultErrorBlockParam.of(
                            BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(BetaDirectCaller.builder().build())
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
    fun ofAdvisorToolResult() {
        val advisorToolResult =
            BetaAdvisorToolResultBlockParam.builder()
                .content(
                    BetaAdvisorToolResultErrorParam.of(
                        BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofAdvisorToolResult(advisorToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("advisorToolResult" to Optional.of(advisorToolResult)))
    }

    @Test
    fun ofAdvisorToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofAdvisorToolResult(
                BetaAdvisorToolResultBlockParam.builder()
                    .content(
                        BetaAdvisorToolResultErrorParam.of(
                            BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofCodeExecutionToolResult() {
        val codeExecutionToolResult =
            BetaCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaCodeExecutionToolResultErrorParam.of(
                        BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam =
            BetaContentBlockParam.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("codeExecutionToolResult" to Optional.of(codeExecutionToolResult)))
    }

    @Test
    fun ofCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofCodeExecutionToolResult(
                BetaCodeExecutionToolResultBlockParam.builder()
                    .content(
                        BetaCodeExecutionToolResultErrorParam.of(
                            BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofBashCodeExecutionToolResult() {
        val bashCodeExecutionToolResult =
            BetaBashCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaBashCodeExecutionToolResultErrorParam.of(
                        BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam =
            BetaContentBlockParam.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(
                mapOf("bashCodeExecutionToolResult" to Optional.of(bashCodeExecutionToolResult))
            )
    }

    @Test
    fun ofBashCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofBashCodeExecutionToolResult(
                BetaBashCodeExecutionToolResultBlockParam.builder()
                    .content(
                        BetaBashCodeExecutionToolResultErrorParam.of(
                            BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofTextEditorCodeExecutionToolResult() {
        val textEditorCodeExecutionToolResult =
            BetaTextEditorCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode
                                .INVALID_TOOL_INPUT
                        )
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam =
            BetaContentBlockParam.ofTextEditorCodeExecutionToolResult(
                textEditorCodeExecutionToolResult
            )

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(
                mapOf(
                    "textEditorCodeExecutionToolResult" to
                        Optional.of(textEditorCodeExecutionToolResult)
                )
            )
    }

    @Test
    fun ofTextEditorCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofTextEditorCodeExecutionToolResult(
                BetaTextEditorCodeExecutionToolResultBlockParam.builder()
                    .content(
                        BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                            .errorCode(
                                BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode
                                    .INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofToolSearchToolResult() {
        val toolSearchToolResult =
            BetaToolSearchToolResultBlockParam.builder()
                .content(
                    BetaToolSearchToolResultErrorParam.builder()
                        .errorCode(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam =
            BetaContentBlockParam.ofToolSearchToolResult(toolSearchToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("toolSearchToolResult" to Optional.of(toolSearchToolResult)))
    }

    @Test
    fun ofToolSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolSearchToolResult(
                BetaToolSearchToolResultBlockParam.builder()
                    .content(
                        BetaToolSearchToolResultErrorParam.builder()
                            .errorCode(
                                BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofMcpToolUse() {
        val mcpToolUse =
            BetaMcpToolUseBlockParam.builder()
                .id("id")
                .input(
                    BetaMcpToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .serverName("server_name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofMcpToolUse(mcpToolUse)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("mcpToolUse" to Optional.of(mcpToolUse)))
    }

    @Test
    fun ofMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofMcpToolUse(
                BetaMcpToolUseBlockParam.builder()
                    .id("id")
                    .input(
                        BetaMcpToolUseBlockParam.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("name")
                    .serverName("server_name")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofMcpToolResult() {
        val mcpToolResult =
            BetaRequestMcpToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("string")
                .isError(true)
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofMcpToolResult(mcpToolResult)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("mcpToolResult" to Optional.of(mcpToolResult)))
    }

    @Test
    fun ofMcpToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofMcpToolResult(
                BetaRequestMcpToolResultBlockParam.builder()
                    .toolUseId("tool_use_id")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
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
    fun ofContainerUpload() {
        val containerUpload =
            BetaContainerUploadBlockParam.builder()
                .fileId("file_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofContainerUpload(containerUpload)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("containerUpload" to Optional.of(containerUpload)))
    }

    @Test
    fun ofContainerUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofContainerUpload(
                BetaContainerUploadBlockParam.builder()
                    .fileId("file_id")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofCompaction() {
        val compaction =
            BetaCompactionBlockParam.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("content")
                .encryptedContent("encrypted_content")
                .signature("signature")
                .addToolChange(
                    BetaRequestToolAdditionBlock.builder()
                        .referenceTool("name")
                        .cacheControl(
                            BetaCacheControlEphemeral.builder()
                                .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofCompaction(compaction)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("compaction" to Optional.of(compaction)))
    }

    @Test
    fun ofCompactionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofCompaction(
                BetaCompactionBlockParam.builder()
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .content("content")
                    .encryptedContent("encrypted_content")
                    .signature("signature")
                    .addToolChange(
                        BetaRequestToolAdditionBlock.builder()
                            .referenceTool("name")
                            .cacheControl(
                                BetaCacheControlEphemeral.builder()
                                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
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
    fun ofToolAddition() {
        val toolAddition =
            BetaRequestToolAdditionBlock.builder()
                .referenceTool("name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolAddition(toolAddition)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("toolAddition" to Optional.of(toolAddition)))
    }

    @Test
    fun ofToolAdditionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolAddition(
                BetaRequestToolAdditionBlock.builder()
                    .referenceTool("name")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofToolRemoval() {
        val toolRemoval =
            BetaRequestToolRemovalBlock.builder()
                .referenceTool("name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolRemoval(toolRemoval)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("toolRemoval" to Optional.of(toolRemoval)))
    }

    @Test
    fun ofToolRemovalRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofToolRemoval(
                BetaRequestToolRemovalBlock.builder()
                    .referenceTool("name")
                    .cacheControl(
                        BetaCacheControlEphemeral.builder()
                            .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofMcpToolListing() {
        val mcpToolListing =
            BetaMcpToolListingBlockParam.builder()
                .mcpServerName("x")
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofMcpToolListing(mcpToolListing)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("mcpToolListing" to Optional.of(mcpToolListing)))
    }

    @Test
    fun ofMcpToolListingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofMcpToolListing(
                BetaMcpToolListingBlockParam.builder()
                    .mcpServerName("x")
                    .addTool(
                        BetaMcpToolParam.builder()
                            .inputSchema(
                                BetaMcpToolParam.InputSchema.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .name("x")
                            .description("description")
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
    fun ofFallback() {
        val fallback =
            BetaFallbackBlockParam.builder()
                .from(BetaFallbackInfoParam.of(Model.CLAUDE_FABLE_5_2))
                .to(BetaFallbackInfoParam.of(Model.CLAUDE_FABLE_5_2))
                .trigger(JsonValue.from(mapOf<String, Any>()))
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofFallback(fallback)

        assertThat(presentVariants(betaContentBlockParam))
            .isEqualTo(mapOf("fallback" to Optional.of(fallback)))
    }

    @Test
    fun ofFallbackRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofFallback(
                BetaFallbackBlockParam.builder()
                    .from(BetaFallbackInfoParam.of(Model.CLAUDE_FABLE_5_2))
                    .to(BetaFallbackInfoParam.of(Model.CLAUDE_FABLE_5_2))
                    .trigger(JsonValue.from(mapOf<String, Any>()))
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
    fun unknownVariantCommonProperties() {
        val betaContentBlockParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cache_control" to mapOf("type" to "ephemeral", "ttl" to "5m"),
                            "title" to "x",
                            "signature" to "signature",
                            "id" to "id",
                            "toolset_name" to "toolset_name",
                            "tool_use_id" to "tool_use_id",
                            "is_error" to true,
                        )
                    ),
                    jacksonTypeRef<BetaContentBlockParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaContentBlockParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaContentBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaContentBlockParam.title()).contains("x")
        assertThat(betaContentBlockParam.signature()).contains("signature")
        assertThat(betaContentBlockParam.id()).contains("id")
        assertThat(betaContentBlockParam.toolsetName()).contains("toolset_name")
        assertThat(betaContentBlockParam.toolUseId()).contains("tool_use_id")
        assertThat(betaContentBlockParam.isError()).contains(true)

        val mismatchedBetaContentBlockParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cache_control" to listOf("invalid"),
                            "title" to listOf("invalid"),
                            "signature" to listOf("invalid"),
                            "id" to listOf("invalid"),
                            "toolset_name" to listOf("invalid"),
                            "tool_use_id" to listOf("invalid"),
                            "is_error" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaContentBlockParam>(),
                )

        assertThat(mismatchedBetaContentBlockParam.cacheControl()).isEmpty
        assertThat(mismatchedBetaContentBlockParam.title()).isEmpty
        assertThat(mismatchedBetaContentBlockParam.signature()).isEmpty
        assertThat(mismatchedBetaContentBlockParam.id()).isEmpty
        assertThat(mismatchedBetaContentBlockParam.toolsetName()).isEmpty
        assertThat(mismatchedBetaContentBlockParam.toolUseId()).isEmpty
        assertThat(mismatchedBetaContentBlockParam.isError()).isEmpty
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

        assertThat(betaContentBlockParam.cacheControl()).isEmpty
        assertThat(betaContentBlockParam.title()).isEmpty
        assertThat(betaContentBlockParam.signature()).isEmpty
        assertThat(betaContentBlockParam.id()).isEmpty
        assertThat(betaContentBlockParam.toolsetName()).isEmpty
        assertThat(betaContentBlockParam.toolUseId()).isEmpty
        assertThat(betaContentBlockParam.isError()).isEmpty
    }

    private fun presentVariants(
        betaContentBlockParam: BetaContentBlockParam
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "text" to betaContentBlockParam.text(),
                "image" to betaContentBlockParam.image(),
                "document" to betaContentBlockParam.document(),
                "searchResult" to betaContentBlockParam.searchResult(),
                "thinking" to betaContentBlockParam.thinking(),
                "redactedThinking" to betaContentBlockParam.redactedThinking(),
                "toolUse" to betaContentBlockParam.toolUse(),
                "toolResult" to betaContentBlockParam.toolResult(),
                "serverToolUse" to betaContentBlockParam.serverToolUse(),
                "webSearchToolResult" to betaContentBlockParam.webSearchToolResult(),
                "webFetchToolResult" to betaContentBlockParam.webFetchToolResult(),
                "advisorToolResult" to betaContentBlockParam.advisorToolResult(),
                "codeExecutionToolResult" to betaContentBlockParam.codeExecutionToolResult(),
                "bashCodeExecutionToolResult" to
                    betaContentBlockParam.bashCodeExecutionToolResult(),
                "textEditorCodeExecutionToolResult" to
                    betaContentBlockParam.textEditorCodeExecutionToolResult(),
                "toolSearchToolResult" to betaContentBlockParam.toolSearchToolResult(),
                "mcpToolUse" to betaContentBlockParam.mcpToolUse(),
                "mcpToolResult" to betaContentBlockParam.mcpToolResult(),
                "containerUpload" to betaContentBlockParam.containerUpload(),
                "compaction" to betaContentBlockParam.compaction(),
                "toolAddition" to betaContentBlockParam.toolAddition(),
                "toolRemoval" to betaContentBlockParam.toolRemoval(),
                "mcpToolListing" to betaContentBlockParam.mcpToolListing(),
                "fallback" to betaContentBlockParam.fallback(),
            )
            .filterValues { it.isPresent }
}
