package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagDeletedBundleTargetTest {

    @Test
    fun create() {
        val betaClaudeTagDeletedBundleTarget = BetaClaudeTagDeletedBundleTarget.of("id")

        assertThat(betaClaudeTagDeletedBundleTarget.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagDeletedBundleTarget = BetaClaudeTagDeletedBundleTarget.of("id")

        val roundtrippedBetaClaudeTagDeletedBundleTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagDeletedBundleTarget),
                jacksonTypeRef<BetaClaudeTagDeletedBundleTarget>(),
            )

        assertThat(roundtrippedBetaClaudeTagDeletedBundleTarget)
            .isEqualTo(betaClaudeTagDeletedBundleTarget)
    }
}
