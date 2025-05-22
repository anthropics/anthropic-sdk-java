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

internal class BetaCodeExecutionToolResultBlockContentTest {

    @Test
    fun ofError() {
        val error =
            BetaCodeExecutionToolResultError.builder()
                .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofError(error)

        assertThat(betaCodeExecutionToolResultBlockContent.error()).contains(error)
        assertThat(betaCodeExecutionToolResultBlockContent.resultBlock()).isEmpty
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofError(
                BetaCodeExecutionToolResultError.builder()
                    .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedBetaCodeExecutionToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlockContent),
                jacksonTypeRef<BetaCodeExecutionToolResultBlockContent>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlockContent)
            .isEqualTo(betaCodeExecutionToolResultBlockContent)
    }

    @Test
    fun ofResultBlock() {
        val resultBlock =
            BetaCodeExecutionResultBlock.builder()
                .addContent(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofResultBlock(resultBlock)

        assertThat(betaCodeExecutionToolResultBlockContent.error()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockContent.resultBlock()).contains(resultBlock)
    }

    @Test
    fun ofResultBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofResultBlock(
                BetaCodeExecutionResultBlock.builder()
                    .addContent(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
                    .returnCode(0L)
                    .stderr("stderr")
                    .stdout("stdout")
                    .build()
            )

        val roundtrippedBetaCodeExecutionToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlockContent),
                jacksonTypeRef<BetaCodeExecutionToolResultBlockContent>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlockContent)
            .isEqualTo(betaCodeExecutionToolResultBlockContent)
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
        val betaCodeExecutionToolResultBlockContent =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaCodeExecutionToolResultBlockContent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaCodeExecutionToolResultBlockContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
