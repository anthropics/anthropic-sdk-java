package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackDefaultSettingSourceTest {

    @Test
    fun create() {
        val betaClaudeTagSlackDefaultSettingSource =
            BetaClaudeTagSlackDefaultSettingSource.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackDefaultSettingSource =
            BetaClaudeTagSlackDefaultSettingSource.builder().build()

        val roundtrippedBetaClaudeTagSlackDefaultSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackDefaultSettingSource),
                jacksonTypeRef<BetaClaudeTagSlackDefaultSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackDefaultSettingSource)
            .isEqualTo(betaClaudeTagSlackDefaultSettingSource)
    }
}
