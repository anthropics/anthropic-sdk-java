package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ClaudeCodeKeyCreatorNotMemberErrorDetailsTest {

    @Test
    fun create() {
        val claudeCodeKeyCreatorNotMemberErrorDetails =
            ClaudeCodeKeyCreatorNotMemberErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val claudeCodeKeyCreatorNotMemberErrorDetails =
            ClaudeCodeKeyCreatorNotMemberErrorDetails.builder().build()

        val roundtrippedClaudeCodeKeyCreatorNotMemberErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(claudeCodeKeyCreatorNotMemberErrorDetails),
                jacksonTypeRef<ClaudeCodeKeyCreatorNotMemberErrorDetails>(),
            )

        assertThat(roundtrippedClaudeCodeKeyCreatorNotMemberErrorDetails)
            .isEqualTo(claudeCodeKeyCreatorNotMemberErrorDetails)
    }
}
