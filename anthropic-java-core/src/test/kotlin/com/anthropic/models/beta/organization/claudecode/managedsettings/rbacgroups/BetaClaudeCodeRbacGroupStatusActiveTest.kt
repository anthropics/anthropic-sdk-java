package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeRbacGroupStatusActiveTest {

    @Test
    fun create() {
        val betaClaudeCodeRbacGroupStatusActive =
            BetaClaudeCodeRbacGroupStatusActive.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupStatusActive =
            BetaClaudeCodeRbacGroupStatusActive.builder().build()

        val roundtrippedBetaClaudeCodeRbacGroupStatusActive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupStatusActive),
                jacksonTypeRef<BetaClaudeCodeRbacGroupStatusActive>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupStatusActive)
            .isEqualTo(betaClaudeCodeRbacGroupStatusActive)
    }
}
