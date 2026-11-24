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

internal class BetaCodeExecutionToolResultBlockContentTest {

    @Test
    fun ofError() {
        val error =
            BetaCodeExecutionToolResultError.of(
                BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
            )

        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofError(error)

        assertThat(presentVariants(betaCodeExecutionToolResultBlockContent))
            .isEqualTo(mapOf("error" to Optional.of(error)))
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofError(
                BetaCodeExecutionToolResultError.of(
                    BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                )
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
                .addContent(BetaCodeExecutionOutputBlock.of("file_id"))
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofResultBlock(resultBlock)

        assertThat(presentVariants(betaCodeExecutionToolResultBlockContent))
            .isEqualTo(mapOf("resultBlock" to Optional.of(resultBlock)))
    }

    @Test
    fun ofResultBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofResultBlock(
                BetaCodeExecutionResultBlock.builder()
                    .addContent(BetaCodeExecutionOutputBlock.of("file_id"))
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

    @Test
    fun ofEncryptedCodeExecutionResultBlock() {
        val encryptedCodeExecutionResultBlock =
            BetaEncryptedCodeExecutionResultBlock.builder()
                .addContent(BetaCodeExecutionOutputBlock.of("file_id"))
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofEncryptedCodeExecutionResultBlock(
                encryptedCodeExecutionResultBlock
            )

        assertThat(presentVariants(betaCodeExecutionToolResultBlockContent))
            .isEqualTo(
                mapOf(
                    "encryptedCodeExecutionResultBlock" to
                        Optional.of(encryptedCodeExecutionResultBlock)
                )
            )
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockContent =
            BetaCodeExecutionToolResultBlockContent.ofEncryptedCodeExecutionResultBlock(
                BetaEncryptedCodeExecutionResultBlock.builder()
                    .addContent(BetaCodeExecutionOutputBlock.of("file_id"))
                    .encryptedStdout("encrypted_stdout")
                    .returnCode(0L)
                    .stderr("stderr")
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

        assertThat(betaCodeExecutionToolResultBlockContent.content()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockContent.returnCode()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockContent.stderr()).isEmpty
    }

    private fun presentVariants(
        betaCodeExecutionToolResultBlockContent: BetaCodeExecutionToolResultBlockContent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "error" to betaCodeExecutionToolResultBlockContent.error(),
                "resultBlock" to betaCodeExecutionToolResultBlockContent.resultBlock(),
                "encryptedCodeExecutionResultBlock" to
                    betaCodeExecutionToolResultBlockContent.encryptedCodeExecutionResultBlock(),
            )
            .filterValues { it.isPresent }
}
