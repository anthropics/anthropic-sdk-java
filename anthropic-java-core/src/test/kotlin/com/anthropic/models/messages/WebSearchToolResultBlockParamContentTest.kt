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

internal class WebSearchToolResultBlockParamContentTest {

    @Test
    fun ofItem() {
        val item =
            listOf(
                WebSearchResultBlockParam.builder()
                    .encryptedContent("encrypted_content")
                    .title("x")
                    .url("x")
                    .pageAge("page_age")
                    .build()
            )

        val webSearchToolResultBlockParamContent = WebSearchToolResultBlockParamContent.ofItem(item)

        assertThat(webSearchToolResultBlockParamContent.item()).contains(item)
        assertThat(webSearchToolResultBlockParamContent.requestError()).isEmpty
    }

    @Test
    fun ofItemRoundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockParamContent =
            WebSearchToolResultBlockParamContent.ofItem(
                listOf(
                    WebSearchResultBlockParam.builder()
                        .encryptedContent("encrypted_content")
                        .title("x")
                        .url("x")
                        .pageAge("page_age")
                        .build()
                )
            )

        val roundtrippedWebSearchToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultBlockParamContent),
                jacksonTypeRef<WebSearchToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedWebSearchToolResultBlockParamContent)
            .isEqualTo(webSearchToolResultBlockParamContent)
    }

    @Test
    fun ofRequestError() {
        val requestError =
            WebSearchToolRequestError.builder()
                .errorCode(WebSearchToolRequestError.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        val webSearchToolResultBlockParamContent =
            WebSearchToolResultBlockParamContent.ofRequestError(requestError)

        assertThat(webSearchToolResultBlockParamContent.item()).isEmpty
        assertThat(webSearchToolResultBlockParamContent.requestError()).contains(requestError)
    }

    @Test
    fun ofRequestErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockParamContent =
            WebSearchToolResultBlockParamContent.ofRequestError(
                WebSearchToolRequestError.builder()
                    .errorCode(WebSearchToolRequestError.ErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedWebSearchToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultBlockParamContent),
                jacksonTypeRef<WebSearchToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedWebSearchToolResultBlockParamContent)
            .isEqualTo(webSearchToolResultBlockParamContent)
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
        val webSearchToolResultBlockParamContent =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<WebSearchToolResultBlockParamContent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                webSearchToolResultBlockParamContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
