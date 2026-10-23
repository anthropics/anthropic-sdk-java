package com.anthropic.models.beta.organization.claudetag.bundles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagBundleTest {

    @Test
    fun create() {
        val betaClaudeTagBundle =
            BetaClaudeTagBundle.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .displayName("display_name")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaClaudeTagBundle.id()).isEqualTo("id")
        assertThat(betaClaudeTagBundle.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeTagBundle.description()).contains("description")
        assertThat(betaClaudeTagBundle.displayName()).isEqualTo("display_name")
        assertThat(betaClaudeTagBundle.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagBundle =
            BetaClaudeTagBundle.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .description("description")
                .displayName("display_name")
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaClaudeTagBundle =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagBundle),
                jacksonTypeRef<BetaClaudeTagBundle>(),
            )

        assertThat(roundtrippedBetaClaudeTagBundle).isEqualTo(betaClaudeTagBundle)
    }
}
