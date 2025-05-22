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

internal class BetaWebSearchToolResultBlockParamContentTest {

    @Test
    fun ofResultBlock() {
        val resultBlock =
            listOf(
                BetaWebSearchResultBlockParam.builder()
                    .encryptedContent("encrypted_content")
                    .title("title")
                    .url("url")
                    .pageAge("page_age")
                    .build()
            )

        val betaWebSearchToolResultBlockParamContent =
            BetaWebSearchToolResultBlockParamContent.ofResultBlock(resultBlock)

        assertThat(betaWebSearchToolResultBlockParamContent.resultBlock()).contains(resultBlock)
        assertThat(betaWebSearchToolResultBlockParamContent.requestError()).isEmpty
    }

    @Test
    fun ofResultBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockParamContent =
            BetaWebSearchToolResultBlockParamContent.ofResultBlock(
                listOf(
                    BetaWebSearchResultBlockParam.builder()
                        .encryptedContent("encrypted_content")
                        .title("title")
                        .url("url")
                        .pageAge("page_age")
                        .build()
                )
            )

        val roundtrippedBetaWebSearchToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultBlockParamContent),
                jacksonTypeRef<BetaWebSearchToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultBlockParamContent)
            .isEqualTo(betaWebSearchToolResultBlockParamContent)
    }

    @Test
    fun ofRequestError() {
        val requestError =
            BetaWebSearchToolRequestError.builder()
                .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val betaWebSearchToolResultBlockParamContent =
            BetaWebSearchToolResultBlockParamContent.ofRequestError(requestError)

        assertThat(betaWebSearchToolResultBlockParamContent.resultBlock()).isEmpty
        assertThat(betaWebSearchToolResultBlockParamContent.requestError()).contains(requestError)
    }

    @Test
    fun ofRequestErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockParamContent =
            BetaWebSearchToolResultBlockParamContent.ofRequestError(
                BetaWebSearchToolRequestError.builder()
                    .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedBetaWebSearchToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultBlockParamContent),
                jacksonTypeRef<BetaWebSearchToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultBlockParamContent)
            .isEqualTo(betaWebSearchToolResultBlockParamContent)
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
        val betaWebSearchToolResultBlockParamContent =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaWebSearchToolResultBlockParamContent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaWebSearchToolResultBlockParamContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
