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

internal class BetaWebSearchToolResultBlockContentTest {

    @Test
    fun ofError() {
        val error =
            BetaWebSearchToolResultError.builder()
                .errorCode(BetaWebSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        val betaWebSearchToolResultBlockContent = BetaWebSearchToolResultBlockContent.ofError(error)

        assertThat(betaWebSearchToolResultBlockContent.error()).contains(error)
        assertThat(betaWebSearchToolResultBlockContent.resultBlocks()).isEmpty
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockContent =
            BetaWebSearchToolResultBlockContent.ofError(
                BetaWebSearchToolResultError.builder()
                    .errorCode(BetaWebSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedBetaWebSearchToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultBlockContent),
                jacksonTypeRef<BetaWebSearchToolResultBlockContent>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultBlockContent)
            .isEqualTo(betaWebSearchToolResultBlockContent)
    }

    @Test
    fun ofResultBlocks() {
        val resultBlocks =
            listOf(
                BetaWebSearchResultBlock.builder()
                    .encryptedContent("encrypted_content")
                    .pageAge("page_age")
                    .title("title")
                    .url("url")
                    .build()
            )

        val betaWebSearchToolResultBlockContent =
            BetaWebSearchToolResultBlockContent.ofResultBlocks(resultBlocks)

        assertThat(betaWebSearchToolResultBlockContent.error()).isEmpty
        assertThat(betaWebSearchToolResultBlockContent.resultBlocks()).contains(resultBlocks)
    }

    @Test
    fun ofResultBlocksRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockContent =
            BetaWebSearchToolResultBlockContent.ofResultBlocks(
                listOf(
                    BetaWebSearchResultBlock.builder()
                        .encryptedContent("encrypted_content")
                        .pageAge("page_age")
                        .title("title")
                        .url("url")
                        .build()
                )
            )

        val roundtrippedBetaWebSearchToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultBlockContent),
                jacksonTypeRef<BetaWebSearchToolResultBlockContent>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultBlockContent)
            .isEqualTo(betaWebSearchToolResultBlockContent)
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
        val betaWebSearchToolResultBlockContent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaWebSearchToolResultBlockContent>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaWebSearchToolResultBlockContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
