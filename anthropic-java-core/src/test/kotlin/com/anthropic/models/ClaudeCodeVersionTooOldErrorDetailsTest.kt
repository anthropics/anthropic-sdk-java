package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ClaudeCodeVersionTooOldErrorDetailsTest {

    @Test
    fun create() {
        val claudeCodeVersionTooOldErrorDetails =
            ClaudeCodeVersionTooOldErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val claudeCodeVersionTooOldErrorDetails =
            ClaudeCodeVersionTooOldErrorDetails.builder().build()

        val roundtrippedClaudeCodeVersionTooOldErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(claudeCodeVersionTooOldErrorDetails),
                jacksonTypeRef<ClaudeCodeVersionTooOldErrorDetails>(),
            )

        assertThat(roundtrippedClaudeCodeVersionTooOldErrorDetails)
            .isEqualTo(claudeCodeVersionTooOldErrorDetails)
    }
}
