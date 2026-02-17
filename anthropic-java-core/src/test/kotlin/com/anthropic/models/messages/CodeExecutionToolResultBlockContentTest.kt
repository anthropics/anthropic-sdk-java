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

internal class CodeExecutionToolResultBlockContentTest {

    @Test
    fun ofError() {
        val error =
            CodeExecutionToolResultError.builder()
                .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val codeExecutionToolResultBlockContent = CodeExecutionToolResultBlockContent.ofError(error)

        assertThat(codeExecutionToolResultBlockContent.error()).contains(error)
        assertThat(codeExecutionToolResultBlockContent.resultBlock()).isEmpty
        assertThat(codeExecutionToolResultBlockContent.encryptedCodeExecutionResultBlock()).isEmpty
    }

    @Test
    fun ofErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockContent =
            CodeExecutionToolResultBlockContent.ofError(
                CodeExecutionToolResultError.builder()
                    .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                    .build()
            )

        val roundtrippedCodeExecutionToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockContent),
                jacksonTypeRef<CodeExecutionToolResultBlockContent>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockContent)
            .isEqualTo(codeExecutionToolResultBlockContent)
    }

    @Test
    fun ofResultBlock() {
        val resultBlock =
            CodeExecutionResultBlock.builder()
                .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val codeExecutionToolResultBlockContent =
            CodeExecutionToolResultBlockContent.ofResultBlock(resultBlock)

        assertThat(codeExecutionToolResultBlockContent.error()).isEmpty
        assertThat(codeExecutionToolResultBlockContent.resultBlock()).contains(resultBlock)
        assertThat(codeExecutionToolResultBlockContent.encryptedCodeExecutionResultBlock()).isEmpty
    }

    @Test
    fun ofResultBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockContent =
            CodeExecutionToolResultBlockContent.ofResultBlock(
                CodeExecutionResultBlock.builder()
                    .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                    .returnCode(0L)
                    .stderr("stderr")
                    .stdout("stdout")
                    .build()
            )

        val roundtrippedCodeExecutionToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockContent),
                jacksonTypeRef<CodeExecutionToolResultBlockContent>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockContent)
            .isEqualTo(codeExecutionToolResultBlockContent)
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlock() {
        val encryptedCodeExecutionResultBlock =
            EncryptedCodeExecutionResultBlock.builder()
                .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val codeExecutionToolResultBlockContent =
            CodeExecutionToolResultBlockContent.ofEncryptedCodeExecutionResultBlock(
                encryptedCodeExecutionResultBlock
            )

        assertThat(codeExecutionToolResultBlockContent.error()).isEmpty
        assertThat(codeExecutionToolResultBlockContent.resultBlock()).isEmpty
        assertThat(codeExecutionToolResultBlockContent.encryptedCodeExecutionResultBlock())
            .contains(encryptedCodeExecutionResultBlock)
    }

    @Test
    fun ofEncryptedCodeExecutionResultBlockRoundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockContent =
            CodeExecutionToolResultBlockContent.ofEncryptedCodeExecutionResultBlock(
                EncryptedCodeExecutionResultBlock.builder()
                    .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                    .encryptedStdout("encrypted_stdout")
                    .returnCode(0L)
                    .stderr("stderr")
                    .build()
            )

        val roundtrippedCodeExecutionToolResultBlockContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockContent),
                jacksonTypeRef<CodeExecutionToolResultBlockContent>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockContent)
            .isEqualTo(codeExecutionToolResultBlockContent)
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
        val codeExecutionToolResultBlockContent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<CodeExecutionToolResultBlockContent>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                codeExecutionToolResultBlockContent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
