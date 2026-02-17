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

internal class CodeExecutionToolResultBlockParamContentTest {

    @Test
    fun ofErrorParam() {
        val errorParam =
            CodeExecutionToolResultErrorParam.builder()
                .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val codeExecutionToolResultBlockParamContent =
            CodeExecutionToolResultBlockParamContent.ofErrorParam(errorParam)

        assertThat(codeExecutionToolResultBlockParamContent.errorParam()).contains(errorParam)
        assertThat(codeExecutionToolResultBlockParamContent.resultBlockParam()).isEmpty
        assertThat(
                codeExecutionToolResultBlockParamContent.encryptedCodeExecutionResultBlockParam()
            )
            .isEmpty
    }

    @Test
    fun ofErrorParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockParamContent =
            CodeExecutionToolResultBlockParamContent.ofErrorParam(
                CodeExecutionToolResultErrorParam.builder()
                    .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedCodeExecutionToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockParamContent),
                jacksonTypeRef<CodeExecutionToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockParamContent)
            .isEqualTo(codeExecutionToolResultBlockParamContent)
    }

    @Test
    fun ofResultBlockParam() {
        val resultBlockParam =
            CodeExecutionResultBlockParam.builder()
                .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val codeExecutionToolResultBlockParamContent =
            CodeExecutionToolResultBlockParamContent.ofResultBlockParam(resultBlockParam)

        assertThat(codeExecutionToolResultBlockParamContent.errorParam()).isEmpty
        assertThat(codeExecutionToolResultBlockParamContent.resultBlockParam())
            .contains(resultBlockParam)
        assertThat(
                codeExecutionToolResultBlockParamContent.encryptedCodeExecutionResultBlockParam()
            )
            .isEmpty
    }

    @Test
    fun ofResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockParamContent =
            CodeExecutionToolResultBlockParamContent.ofResultBlockParam(
                CodeExecutionResultBlockParam.builder()
                    .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                    .returnCode(0L)
                    .stderr("stderr")
                    .stdout("stdout")
                    .build()
            )

        val roundtrippedCodeExecutionToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockParamContent),
                jacksonTypeRef<CodeExecutionToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockParamContent)
            .isEqualTo(codeExecutionToolResultBlockParamContent)
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockParam() {
        val encryptedCodeExecutionResultBlockParam =
            EncryptedCodeExecutionResultBlockParam.builder()
                .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val codeExecutionToolResultBlockParamContent =
            CodeExecutionToolResultBlockParamContent.ofEncryptedCodeExecutionResultBlockParam(
                encryptedCodeExecutionResultBlockParam
            )

        assertThat(codeExecutionToolResultBlockParamContent.errorParam()).isEmpty
        assertThat(codeExecutionToolResultBlockParamContent.resultBlockParam()).isEmpty
        assertThat(
                codeExecutionToolResultBlockParamContent.encryptedCodeExecutionResultBlockParam()
            )
            .contains(encryptedCodeExecutionResultBlockParam)
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockParamRoundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockParamContent =
            CodeExecutionToolResultBlockParamContent.ofEncryptedCodeExecutionResultBlockParam(
                EncryptedCodeExecutionResultBlockParam.builder()
                    .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                    .encryptedStdout("encrypted_stdout")
                    .returnCode(0L)
                    .stderr("stderr")
                    .build()
            )

        val roundtrippedCodeExecutionToolResultBlockParamContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockParamContent),
                jacksonTypeRef<CodeExecutionToolResultBlockParamContent>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockParamContent)
            .isEqualTo(codeExecutionToolResultBlockParamContent)
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
        val codeExecutionToolResultBlockParamContent =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<CodeExecutionToolResultBlockParamContent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                codeExecutionToolResultBlockParamContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
