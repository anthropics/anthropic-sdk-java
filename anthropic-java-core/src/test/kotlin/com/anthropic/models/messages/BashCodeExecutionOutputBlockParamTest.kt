// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionOutputBlockParamTest {

    @Test
    fun create() {
        val bashCodeExecutionOutputBlockParam =
            BashCodeExecutionOutputBlockParam.builder().fileId("file_id").build()

        assertThat(bashCodeExecutionOutputBlockParam.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionOutputBlockParam =
            BashCodeExecutionOutputBlockParam.builder().fileId("file_id").build()

        val roundtrippedBashCodeExecutionOutputBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionOutputBlockParam),
                jacksonTypeRef<BashCodeExecutionOutputBlockParam>(),
            )

        assertThat(roundtrippedBashCodeExecutionOutputBlockParam)
            .isEqualTo(bashCodeExecutionOutputBlockParam)
    }
}
