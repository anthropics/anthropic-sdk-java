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

internal class WebSearchToolResultBlockParamContentTest {

    @Test
    fun ofItem() {
        val item =
            listOf(
                WebSearchResultBlockParam.builder()
                    .encryptedContent("encrypted_content")
                    .title("title")
                    .url("url")
                    .pageAge("page_age")
                    .build()
            )

        val webSearchToolResultBlockParamContent = WebSearchToolResultBlockParamContent.ofItem(item)

        assertThat(presentVariants(webSearchToolResultBlockParamContent))
            .isEqualTo(mapOf("item" to Optional.of(item)))
    }

    @Test
    fun ofItemRoundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockParamContent =
            WebSearchToolResultBlockParamContent.ofItem(
                listOf(
                    WebSearchResultBlockParam.builder()
                        .encryptedContent("encrypted_content")
                        .title("title")
                        .url("url")
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
            WebSearchToolRequestError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)

        val webSearchToolResultBlockParamContent =
            WebSearchToolResultBlockParamContent.ofRequestError(requestError)

        assertThat(presentVariants(webSearchToolResultBlockParamContent))
            .isEqualTo(mapOf("requestError" to Optional.of(requestError)))
    }

    @Test
    fun ofRequestErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlockParamContent =
            WebSearchToolResultBlockParamContent.ofRequestError(
                WebSearchToolRequestError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
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

    private fun presentVariants(
        webSearchToolResultBlockParamContent: WebSearchToolResultBlockParamContent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "item" to webSearchToolResultBlockParamContent.item(),
                "requestError" to webSearchToolResultBlockParamContent.requestError(),
            )
            .filterValues { it.isPresent }
}
