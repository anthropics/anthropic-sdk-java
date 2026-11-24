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

internal class BetaCodeExecutionToolResultBlockParamContentTest {

    @Test
    fun ofErrorParam() {
        val errorParam =
            BetaCodeExecutionToolResultErrorParam.of(
                BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
            )

        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(errorParam)

        assertThat(presentVariants(betaCodeExecutionToolResultBlockParamContent))
            .isEqualTo(mapOf("errorParam" to Optional.of(errorParam)))
    }

    @Test
    fun ofErrorParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(
                BetaCodeExecutionToolResultErrorParam.of(
                    BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                )
            )

        val roundtrippedBetaCodeExecutionToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlockParamContent),
                jacksonTypeRef<BetaCodeExecutionToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlockParamContent)
            .isEqualTo(betaCodeExecutionToolResultBlockParamContent)
    }

    @Test
    fun ofResultBlockParam() {
        val resultBlockParam =
            BetaCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.of("file_id"))
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(resultBlockParam)

        assertThat(presentVariants(betaCodeExecutionToolResultBlockParamContent))
            .isEqualTo(mapOf("resultBlockParam" to Optional.of(resultBlockParam)))
    }

    @Test
    fun ofResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(
                BetaCodeExecutionResultBlockParam.builder()
                    .addContent(BetaCodeExecutionOutputBlockParam.of("file_id"))
                    .returnCode(0L)
                    .stderr("stderr")
                    .stdout("stdout")
                    .build()
            )

        val roundtrippedBetaCodeExecutionToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlockParamContent),
                jacksonTypeRef<BetaCodeExecutionToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlockParamContent)
            .isEqualTo(betaCodeExecutionToolResultBlockParamContent)
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockParam() {
        val encryptedCodeExecutionResultBlockParam =
            BetaEncryptedCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.of("file_id"))
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofEncryptedCodeExecutionResultBlockParam(
                encryptedCodeExecutionResultBlockParam
            )

        assertThat(presentVariants(betaCodeExecutionToolResultBlockParamContent))
            .isEqualTo(
                mapOf(
                    "encryptedCodeExecutionResultBlockParam" to
                        Optional.of(encryptedCodeExecutionResultBlockParam)
                )
            )
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofEncryptedCodeExecutionResultBlockParam(
                BetaEncryptedCodeExecutionResultBlockParam.builder()
                    .addContent(BetaCodeExecutionOutputBlockParam.of("file_id"))
                    .encryptedStdout("encrypted_stdout")
                    .returnCode(0L)
                    .stderr("stderr")
                    .build()
            )

        val roundtrippedBetaCodeExecutionToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlockParamContent),
                jacksonTypeRef<BetaCodeExecutionToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlockParamContent)
            .isEqualTo(betaCodeExecutionToolResultBlockParamContent)
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
        val betaCodeExecutionToolResultBlockParamContent =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaCodeExecutionToolResultBlockParamContent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaCodeExecutionToolResultBlockParamContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaCodeExecutionToolResultBlockParamContent.content()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockParamContent.returnCode()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockParamContent.stderr()).isEmpty
    }

    private fun presentVariants(
        betaCodeExecutionToolResultBlockParamContent: BetaCodeExecutionToolResultBlockParamContent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "errorParam" to betaCodeExecutionToolResultBlockParamContent.errorParam(),
                "resultBlockParam" to
                    betaCodeExecutionToolResultBlockParamContent.resultBlockParam(),
                "encryptedCodeExecutionResultBlockParam" to
                    betaCodeExecutionToolResultBlockParamContent
                        .encryptedCodeExecutionResultBlockParam(),
            )
            .filterValues { it.isPresent }
}
