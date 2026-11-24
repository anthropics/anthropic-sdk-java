package com.anthropic.models.beta.messages

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

internal class BetaWebSearchToolResultBlockContentTest {

    @Test
    fun ofError() {
        val error =
            BetaWebSearchToolResultError.of(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)

        val betaWebSearchToolResultBlockContent = BetaWebSearchToolResultBlockContent.ofError(error)

        assertThat(presentVariants(betaWebSearchToolResultBlockContent))
            .isEqualTo(mapOf("error" to Optional.of(error)))
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlockContent =
            BetaWebSearchToolResultBlockContent.ofError(
                BetaWebSearchToolResultError.of(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
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

        assertThat(presentVariants(betaWebSearchToolResultBlockContent))
            .isEqualTo(mapOf("resultBlocks" to Optional.of(resultBlocks)))
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

    private fun presentVariants(
        betaWebSearchToolResultBlockContent: BetaWebSearchToolResultBlockContent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "error" to betaWebSearchToolResultBlockContent.error(),
                "resultBlocks" to betaWebSearchToolResultBlockContent.resultBlocks(),
            )
            .filterValues { it.isPresent }
}
