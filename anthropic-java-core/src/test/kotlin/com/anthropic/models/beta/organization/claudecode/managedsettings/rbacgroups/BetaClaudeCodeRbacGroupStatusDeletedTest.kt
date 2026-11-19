package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeRbacGroupStatusDeletedTest {

    @Test
    fun create() {
        val betaClaudeCodeRbacGroupStatusDeleted =
            BetaClaudeCodeRbacGroupStatusDeleted.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupStatusDeleted =
            BetaClaudeCodeRbacGroupStatusDeleted.builder().build()

        val roundtrippedBetaClaudeCodeRbacGroupStatusDeleted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupStatusDeleted),
                jacksonTypeRef<BetaClaudeCodeRbacGroupStatusDeleted>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupStatusDeleted)
            .isEqualTo(betaClaudeCodeRbacGroupStatusDeleted)
    }
}
