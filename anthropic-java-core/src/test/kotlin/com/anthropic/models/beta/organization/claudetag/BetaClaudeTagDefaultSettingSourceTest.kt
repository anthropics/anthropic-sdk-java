package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagDefaultSettingSourceTest {

    @Test
    fun create() {
        val betaClaudeTagDefaultSettingSource = BetaClaudeTagDefaultSettingSource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagDefaultSettingSource = BetaClaudeTagDefaultSettingSource.builder().build()

        val roundtrippedBetaClaudeTagDefaultSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagDefaultSettingSource),
                jacksonTypeRef<BetaClaudeTagDefaultSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagDefaultSettingSource)
            .isEqualTo(betaClaudeTagDefaultSettingSource)
    }
}
