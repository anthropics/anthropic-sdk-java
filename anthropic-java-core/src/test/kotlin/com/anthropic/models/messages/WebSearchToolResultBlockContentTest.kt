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

internal class WebSearchToolResultBlockContentTest {

    @Test
    fun ofError() {
        val error =
            WebSearchToolResultError.builder()
                .errorCode(WebSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        val webSearchToolResultBlockContent = WebSearchToolResultBlockContent.ofError(error)

        assertThat(webSearchToolResultBlockContent.error()).contains(error)
        assertThat(webSearchToolResultBlockContent.resultBlocks()).isEmpty
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockContent =
            WebSearchToolResultBlockContent.ofError(
                WebSearchToolResultError.builder()
                    .errorCode(WebSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedWebSearchToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultBlockContent),
                jacksonTypeRef<WebSearchToolResultBlockContent>(),
            )

        assertThat(roundtrippedWebSearchToolResultBlockContent)
            .isEqualTo(webSearchToolResultBlockContent)
    }

    @Test
    fun ofResultBlocks() {
        val resultBlocks =
            listOf(
                WebSearchResultBlock.builder()
                    .encryptedContent("encrypted_content")
                    .pageAge("page_age")
                    .title("title")
                    .url("url")
                    .build()
            )

        val webSearchToolResultBlockContent =
            WebSearchToolResultBlockContent.ofResultBlocks(resultBlocks)

        assertThat(webSearchToolResultBlockContent.error()).isEmpty
        assertThat(webSearchToolResultBlockContent.resultBlocks()).contains(resultBlocks)
    }

    @Test
    fun ofResultBlocksRoundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockContent =
            WebSearchToolResultBlockContent.ofResultBlocks(
                listOf(
                    WebSearchResultBlock.builder()
                        .encryptedContent("encrypted_content")
                        .pageAge("page_age")
                        .title("title")
                        .url("url")
                        .build()
                )
            )

        val roundtrippedWebSearchToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultBlockContent),
                jacksonTypeRef<WebSearchToolResultBlockContent>(),
            )

        assertThat(roundtrippedWebSearchToolResultBlockContent)
            .isEqualTo(webSearchToolResultBlockContent)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val webSearchToolResultBlockContent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<WebSearchToolResultBlockContent>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                webSearchToolResultBlockContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
