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
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
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
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofImage(image)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).contains(image)
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).contains(document)
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                                .citedText("cited_text")
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

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).contains(searchResult)
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                                    .citedText("cited_text")
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

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).contains(thinking)
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).contains(redactedThinking)
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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

    @Test
    fun ofToolUse() {
        val toolUse =
            BetaToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolUse(toolUse)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).contains(toolUse)
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofToolResult(toolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).contains(toolResult)
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                .name(BetaServerToolUseBlockParam.Name.WEB_SEARCH)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofServerToolUse(serverToolUse)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).contains(serverToolUse)
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofServerToolUse(
                BetaServerToolUseBlockParam.builder()
                    .id("srvtoolu_SQfNkl1n_JR_")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name(BetaServerToolUseBlockParam.Name.WEB_SEARCH)
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
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofWebSearchToolResult(webSearchToolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).contains(webSearchToolResult)
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
    fun ofWebFetchToolResult() {
        val webFetchToolResult =
            BetaWebFetchToolResultBlockParam.builder()
                .content(
                    BetaWebFetchToolResultErrorBlockParam.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofWebFetchToolResult(webFetchToolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).contains(webFetchToolResult)
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
    }

    @Test
    fun ofWebFetchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofWebFetchToolResult(
                BetaWebFetchToolResultBlockParam.builder()
                    .content(
                        BetaWebFetchToolResultErrorBlockParam.builder()
                            .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                    BetaCodeExecutionToolResultErrorParam.builder()
                        .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam =
            BetaContentBlockParam.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult())
            .contains(codeExecutionToolResult)
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
    }

    @Test
    fun ofCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofCodeExecutionToolResult(
                BetaCodeExecutionToolResultBlockParam.builder()
                    .content(
                        BetaCodeExecutionToolResultErrorParam.builder()
                            .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                    BetaBashCodeExecutionToolResultErrorParam.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam =
            BetaContentBlockParam.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult())
            .contains(bashCodeExecutionToolResult)
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
    }

    @Test
    fun ofBashCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofBashCodeExecutionToolResult(
                BetaBashCodeExecutionToolResultBlockParam.builder()
                    .content(
                        BetaBashCodeExecutionToolResultErrorParam.builder()
                            .errorCode(
                                BetaBashCodeExecutionToolResultErrorParam.ErrorCode
                                    .INVALID_TOOL_INPUT
                            )
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
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

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult())
            .contains(textEditorCodeExecutionToolResult)
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val betaContentBlockParam = BetaContentBlockParam.ofMcpToolUse(mcpToolUse)

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).contains(mcpToolUse)
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
    }

    @Test
    fun ofMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockParam =
            BetaContentBlockParam.ofMcpToolUse(
                BetaMcpToolUseBlockParam.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf<String, Any>()))
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

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).contains(mcpToolResult)
        assertThat(betaContentBlockParam.containerUpload()).isEmpty
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

        assertThat(betaContentBlockParam.text()).isEmpty
        assertThat(betaContentBlockParam.image()).isEmpty
        assertThat(betaContentBlockParam.document()).isEmpty
        assertThat(betaContentBlockParam.searchResult()).isEmpty
        assertThat(betaContentBlockParam.thinking()).isEmpty
        assertThat(betaContentBlockParam.redactedThinking()).isEmpty
        assertThat(betaContentBlockParam.toolUse()).isEmpty
        assertThat(betaContentBlockParam.toolResult()).isEmpty
        assertThat(betaContentBlockParam.serverToolUse()).isEmpty
        assertThat(betaContentBlockParam.webSearchToolResult()).isEmpty
        assertThat(betaContentBlockParam.webFetchToolResult()).isEmpty
        assertThat(betaContentBlockParam.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlockParam.mcpToolUse()).isEmpty
        assertThat(betaContentBlockParam.mcpToolResult()).isEmpty
        assertThat(betaContentBlockParam.containerUpload()).contains(containerUpload)
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
