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

internal class BetaCodeExecutionToolResultBlockParamContentTest {

    @Test
    fun ofErrorParam() {
        val errorParam =
            BetaCodeExecutionToolResultErrorParam.builder()
                .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(errorParam)

        assertThat(betaCodeExecutionToolResultBlockParamContent.errorParam()).contains(errorParam)
        assertThat(betaCodeExecutionToolResultBlockParamContent.resultBlockParam()).isEmpty
        assertThat(
                betaCodeExecutionToolResultBlockParamContent
                    .encryptedCodeExecutionResultBlockParam()
            )
            .isEmpty
    }

    @Test
    fun ofErrorParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(
                BetaCodeExecutionToolResultErrorParam.builder()
                    .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
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
    fun ofResultBlockParam() {
        val resultBlockParam =
            BetaCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(resultBlockParam)

        assertThat(betaCodeExecutionToolResultBlockParamContent.errorParam()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockParamContent.resultBlockParam())
            .contains(resultBlockParam)
        assertThat(
                betaCodeExecutionToolResultBlockParamContent
                    .encryptedCodeExecutionResultBlockParam()
            )
            .isEmpty
    }

    @Test
    fun ofResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(
                BetaCodeExecutionResultBlockParam.builder()
                    .addContent(
                        BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build()
                    )
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
                .addContent(BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofEncryptedCodeExecutionResultBlockParam(
                encryptedCodeExecutionResultBlockParam
            )

        assertThat(betaCodeExecutionToolResultBlockParamContent.errorParam()).isEmpty
        assertThat(betaCodeExecutionToolResultBlockParamContent.resultBlockParam()).isEmpty
        assertThat(
                betaCodeExecutionToolResultBlockParamContent
                    .encryptedCodeExecutionResultBlockParam()
            )
            .contains(encryptedCodeExecutionResultBlockParam)
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParamContent =
            BetaCodeExecutionToolResultBlockParamContent.ofEncryptedCodeExecutionResultBlockParam(
                BetaEncryptedCodeExecutionResultBlockParam.builder()
                    .addContent(
                        BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build()
                    )
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
    }
}
