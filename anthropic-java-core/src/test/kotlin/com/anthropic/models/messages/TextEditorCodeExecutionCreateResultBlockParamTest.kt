// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionCreateResultBlockParamTest {

    @Test
    fun create() {
        val textEditorCodeExecutionCreateResultBlockParam =
            TextEditorCodeExecutionCreateResultBlockParam.builder().isFileUpdate(true).build()

        assertThat(textEditorCodeExecutionCreateResultBlockParam.isFileUpdate()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionCreateResultBlockParam =
            TextEditorCodeExecutionCreateResultBlockParam.builder().isFileUpdate(true).build()

        val roundtrippedTextEditorCodeExecutionCreateResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionCreateResultBlockParam),
                jacksonTypeRef<TextEditorCodeExecutionCreateResultBlockParam>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionCreateResultBlockParam)
            .isEqualTo(textEditorCodeExecutionCreateResultBlockParam)
    }
}
