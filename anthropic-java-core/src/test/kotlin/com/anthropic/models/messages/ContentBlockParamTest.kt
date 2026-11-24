package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .addCitation(
                    CitationCharLocationParam.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("x")
                        .endCharIndex(0L)
                        .startCharIndex(0L)
                        .build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofText(text)

        assertThat(presentVariants(contentBlockParam)).isEqualTo(mapOf("text" to Optional.of(text)))
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofText(
                TextBlockParam.builder()
                    .text("x")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .addCitation(
                        CitationCharLocationParam.builder()
                            .citedText("The grass is green. The sky is blue.")
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
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .transformations(
                    ImageTransformationsParam.builder()
                        .oversizedImage(ImageTransformationsParam.OversizedImage.DOWNSIZE)
                        .build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofImage(image)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("image" to Optional.of(image)))
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
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .transformations(
                        ImageTransformationsParam.builder()
                            .oversizedImage(ImageTransformationsParam.OversizedImage.DOWNSIZE)
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
    fun ofDocument() {
        val document =
            DocumentBlockParam.builder()
                .base64Source("U3RhaW5sZXNzIHJvY2tz")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .context("x")
                .title("x")
                .build()

        val contentBlockParam = ContentBlockParam.ofDocument(document)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("document" to Optional.of(document)))
    }

    @Test
    fun ofDocumentRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofDocument(
                DocumentBlockParam.builder()
                    .base64Source("U3RhaW5sZXNzIHJvY2tz")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
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
    fun ofSearchResult() {
        val searchResult =
            SearchResultBlockParam.builder()
                .addContent(
                    TextBlockParam.builder()
                        .text("x")
                        .cacheControl(
                            CacheControlEphemeral.builder()
                                .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                .build()
                        )
                        .addCitation(
                            CitationCharLocationParam.builder()
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
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .build()

        val contentBlockParam = ContentBlockParam.ofSearchResult(searchResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("searchResult" to Optional.of(searchResult)))
    }

    @Test
    fun ofSearchResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofSearchResult(
                SearchResultBlockParam.builder()
                    .addContent(
                        TextBlockParam.builder()
                            .text("x")
                            .cacheControl(
                                CacheControlEphemeral.builder()
                                    .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                                    .build()
                            )
                            .addCitation(
                                CitationCharLocationParam.builder()
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
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .citations(CitationsConfigParam.builder().enabled(true).build())
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

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("thinking" to Optional.of(thinking)))
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
        val redactedThinking = RedactedThinkingBlockParam.of("data")

        val contentBlockParam = ContentBlockParam.ofRedactedThinking(redactedThinking)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("redactedThinking" to Optional.of(redactedThinking)))
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofRedactedThinking(RedactedThinkingBlockParam.of("data"))

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
                .input(
                    ToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .toolsetName("toolset_name")
                .build()

        val contentBlockParam = ContentBlockParam.ofToolUse(toolUse)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("toolUse" to Optional.of(toolUse)))
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofToolUse(
                ToolUseBlockParam.builder()
                    .id("id")
                    .input(
                        ToolUseBlockParam.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("x")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(DirectCaller.builder().build())
                    .toolsetName("toolset_name")
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
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .content("string")
                .isError(true)
                .toolsetName("toolset_name")
                .build()

        val contentBlockParam = ContentBlockParam.ofToolResult(toolResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("toolResult" to Optional.of(toolResult)))
    }

    @Test
    fun ofToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofToolResult(
                ToolResultBlockParam.builder()
                    .toolUseId("tool_use_id")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .content("string")
                    .isError(true)
                    .toolsetName("toolset_name")
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
                .id("srvtoolu_c")
                .input(
                    ServerToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(ServerToolUseBlockParam.Name.WEB_SEARCH)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofServerToolUse(serverToolUse)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("serverToolUse" to Optional.of(serverToolUse)))
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofServerToolUse(
                ServerToolUseBlockParam.builder()
                    .id("srvtoolu_c")
                    .input(
                        ServerToolUseBlockParam.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name(ServerToolUseBlockParam.Name.WEB_SEARCH)
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(DirectCaller.builder().build())
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
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofWebSearchToolResult(webSearchToolResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("webSearchToolResult" to Optional.of(webSearchToolResult)))
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
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(DirectCaller.builder().build())
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
    fun ofWebFetchToolResult() {
        val webFetchToolResult =
            WebFetchToolResultBlockParam.builder()
                .content(
                    WebFetchToolResultErrorBlockParam.of(
                        WebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        val contentBlockParam = ContentBlockParam.ofWebFetchToolResult(webFetchToolResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("webFetchToolResult" to Optional.of(webFetchToolResult)))
    }

    @Test
    fun ofWebFetchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofWebFetchToolResult(
                WebFetchToolResultBlockParam.builder()
                    .content(
                        WebFetchToolResultErrorBlockParam.of(
                            WebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
                            .build()
                    )
                    .caller(DirectCaller.builder().build())
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
    fun ofCodeExecutionToolResult() {
        val codeExecutionToolResult =
            CodeExecutionToolResultBlockParam.builder()
                .content(
                    CodeExecutionToolResultErrorParam.of(
                        CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("codeExecutionToolResult" to Optional.of(codeExecutionToolResult)))
    }

    @Test
    fun ofCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofCodeExecutionToolResult(
                CodeExecutionToolResultBlockParam.builder()
                    .content(
                        CodeExecutionToolResultErrorParam.of(
                            CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofBashCodeExecutionToolResult() {
        val bashCodeExecutionToolResult =
            BashCodeExecutionToolResultBlockParam.builder()
                .content(
                    BashCodeExecutionToolResultErrorParam.of(
                        BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val contentBlockParam =
            ContentBlockParam.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(
                mapOf("bashCodeExecutionToolResult" to Optional.of(bashCodeExecutionToolResult))
            )
    }

    @Test
    fun ofBashCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofBashCodeExecutionToolResult(
                BashCodeExecutionToolResultBlockParam.builder()
                    .content(
                        BashCodeExecutionToolResultErrorParam.of(
                            BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofTextEditorCodeExecutionToolResult() {
        val textEditorCodeExecutionToolResult =
            TextEditorCodeExecutionToolResultBlockParam.builder()
                .content(
                    TextEditorCodeExecutionToolResultErrorParam.builder()
                        .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val contentBlockParam =
            ContentBlockParam.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)

        assertThat(presentVariants(contentBlockParam))
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
        val contentBlockParam =
            ContentBlockParam.ofTextEditorCodeExecutionToolResult(
                TextEditorCodeExecutionToolResultBlockParam.builder()
                    .content(
                        TextEditorCodeExecutionToolResultErrorParam.builder()
                            .errorCode(
                                TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofToolSearchToolResult() {
        val toolSearchToolResult =
            ToolSearchToolResultBlockParam.builder()
                .content(
                    ToolSearchToolResultErrorParam.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofToolSearchToolResult(toolSearchToolResult)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("toolSearchToolResult" to Optional.of(toolSearchToolResult)))
    }

    @Test
    fun ofToolSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofToolSearchToolResult(
                ToolSearchToolResultBlockParam.builder()
                    .content(
                        ToolSearchToolResultErrorParam.builder()
                            .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_c")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun ofContainerUpload() {
        val containerUpload =
            ContainerUploadBlockParam.builder()
                .fileId("file_id")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val contentBlockParam = ContentBlockParam.ofContainerUpload(containerUpload)

        assertThat(presentVariants(contentBlockParam))
            .isEqualTo(mapOf("containerUpload" to Optional.of(containerUpload)))
    }

    @Test
    fun ofContainerUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlockParam =
            ContentBlockParam.ofContainerUpload(
                ContainerUploadBlockParam.builder()
                    .fileId("file_id")
                    .cacheControl(
                        CacheControlEphemeral.builder()
                            .ttl(CacheControlEphemeral.Ttl.TTL_5M)
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
    fun unknownVariantCommonProperties() {
        val contentBlockParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cache_control" to mapOf("type" to "ephemeral", "ttl" to "5m"),
                            "title" to "x",
                            "id" to "id",
                            "toolset_name" to "toolset_name",
                            "tool_use_id" to "tool_use_id",
                        )
                    ),
                    jacksonTypeRef<ContentBlockParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { contentBlockParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(contentBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(contentBlockParam.title()).contains("x")
        assertThat(contentBlockParam.id()).contains("id")
        assertThat(contentBlockParam.toolsetName()).contains("toolset_name")
        assertThat(contentBlockParam.toolUseId()).contains("tool_use_id")

        val mismatchedContentBlockParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cache_control" to listOf("invalid"),
                            "title" to listOf("invalid"),
                            "id" to listOf("invalid"),
                            "toolset_name" to listOf("invalid"),
                            "tool_use_id" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<ContentBlockParam>(),
                )

        assertThat(mismatchedContentBlockParam.cacheControl()).isEmpty
        assertThat(mismatchedContentBlockParam.title()).isEmpty
        assertThat(mismatchedContentBlockParam.id()).isEmpty
        assertThat(mismatchedContentBlockParam.toolsetName()).isEmpty
        assertThat(mismatchedContentBlockParam.toolUseId()).isEmpty
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

        assertThat(contentBlockParam.cacheControl()).isEmpty
        assertThat(contentBlockParam.title()).isEmpty
        assertThat(contentBlockParam.id()).isEmpty
        assertThat(contentBlockParam.toolsetName()).isEmpty
        assertThat(contentBlockParam.toolUseId()).isEmpty
    }

    private fun presentVariants(contentBlockParam: ContentBlockParam): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "text" to contentBlockParam.text(),
                "image" to contentBlockParam.image(),
                "document" to contentBlockParam.document(),
                "searchResult" to contentBlockParam.searchResult(),
                "thinking" to contentBlockParam.thinking(),
                "redactedThinking" to contentBlockParam.redactedThinking(),
                "toolUse" to contentBlockParam.toolUse(),
                "toolResult" to contentBlockParam.toolResult(),
                "serverToolUse" to contentBlockParam.serverToolUse(),
                "webSearchToolResult" to contentBlockParam.webSearchToolResult(),
                "webFetchToolResult" to contentBlockParam.webFetchToolResult(),
                "codeExecutionToolResult" to contentBlockParam.codeExecutionToolResult(),
                "bashCodeExecutionToolResult" to contentBlockParam.bashCodeExecutionToolResult(),
                "textEditorCodeExecutionToolResult" to
                    contentBlockParam.textEditorCodeExecutionToolResult(),
                "toolSearchToolResult" to contentBlockParam.toolSearchToolResult(),
                "containerUpload" to contentBlockParam.containerUpload(),
            )
            .filterValues { it.isPresent }
}
