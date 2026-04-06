// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionCreateResultBlockTest {

    @Test
    fun create() {
        val textEditorCodeExecutionCreateResultBlock =
            TextEditorCodeExecutionCreateResultBlock.builder().isFileUpdate(true).build()

        assertThat(textEditorCodeExecutionCreateResultBlock.isFileUpdate()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionCreateResultBlock =
            TextEditorCodeExecutionCreateResultBlock.builder().isFileUpdate(true).build()

        val roundtrippedTextEditorCodeExecutionCreateResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionCreateResultBlock),
                jacksonTypeRef<TextEditorCodeExecutionCreateResultBlock>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionCreateResultBlock)
            .isEqualTo(textEditorCodeExecutionCreateResultBlock)
    }
}
