package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagWritesNotEnabledErrorDetailsTest {

    @Test
    fun create() {
        val betaClaudeTagWritesNotEnabledErrorDetails =
            BetaClaudeTagWritesNotEnabledErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagWritesNotEnabledErrorDetails =
            BetaClaudeTagWritesNotEnabledErrorDetails.builder().build()

        val roundtrippedBetaClaudeTagWritesNotEnabledErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagWritesNotEnabledErrorDetails),
                jacksonTypeRef<BetaClaudeTagWritesNotEnabledErrorDetails>(),
            )

        assertThat(roundtrippedBetaClaudeTagWritesNotEnabledErrorDetails)
            .isEqualTo(betaClaudeTagWritesNotEnabledErrorDetails)
    }
}
