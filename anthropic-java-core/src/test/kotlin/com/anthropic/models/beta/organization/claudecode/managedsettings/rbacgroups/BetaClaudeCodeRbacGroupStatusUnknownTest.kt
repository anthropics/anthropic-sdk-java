package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeRbacGroupStatusUnknownTest {

    @Test
    fun create() {
        val betaClaudeCodeRbacGroupStatusUnknown =
            BetaClaudeCodeRbacGroupStatusUnknown.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupStatusUnknown =
            BetaClaudeCodeRbacGroupStatusUnknown.builder().build()

        val roundtrippedBetaClaudeCodeRbacGroupStatusUnknown =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupStatusUnknown),
                jacksonTypeRef<BetaClaudeCodeRbacGroupStatusUnknown>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupStatusUnknown)
            .isEqualTo(betaClaudeCodeRbacGroupStatusUnknown)
    }
}
