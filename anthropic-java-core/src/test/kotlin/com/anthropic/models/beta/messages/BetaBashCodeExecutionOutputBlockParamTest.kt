// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionOutputBlockParamTest {

    @Test
    fun create() {
        val betaBashCodeExecutionOutputBlockParam =
            BetaBashCodeExecutionOutputBlockParam.of("file_id")

        assertThat(betaBashCodeExecutionOutputBlockParam.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionOutputBlockParam =
            BetaBashCodeExecutionOutputBlockParam.of("file_id")

        val roundtrippedBetaBashCodeExecutionOutputBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionOutputBlockParam),
                jacksonTypeRef<BetaBashCodeExecutionOutputBlockParam>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionOutputBlockParam)
            .isEqualTo(betaBashCodeExecutionOutputBlockParam)
    }
}
