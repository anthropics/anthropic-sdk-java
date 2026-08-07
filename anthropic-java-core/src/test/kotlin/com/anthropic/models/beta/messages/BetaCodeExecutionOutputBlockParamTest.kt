// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionOutputBlockParamTest {

    @Test
    fun create() {
        val betaCodeExecutionOutputBlockParam = BetaCodeExecutionOutputBlockParam.of("file_id")

        assertThat(betaCodeExecutionOutputBlockParam.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionOutputBlockParam = BetaCodeExecutionOutputBlockParam.of("file_id")

        val roundtrippedBetaCodeExecutionOutputBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionOutputBlockParam),
                jacksonTypeRef<BetaCodeExecutionOutputBlockParam>(),
            )

        assertThat(roundtrippedBetaCodeExecutionOutputBlockParam)
            .isEqualTo(betaCodeExecutionOutputBlockParam)
    }
}
