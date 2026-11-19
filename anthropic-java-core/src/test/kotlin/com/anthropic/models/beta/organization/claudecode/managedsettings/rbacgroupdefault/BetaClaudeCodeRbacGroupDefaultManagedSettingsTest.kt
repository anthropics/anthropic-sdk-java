package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeRbacGroupDefaultManagedSettingsTest {

    @Test
    fun create() {
        val betaClaudeCodeRbacGroupDefaultManagedSettings =
            BetaClaudeCodeRbacGroupDefaultManagedSettings.builder()
                .settings(
                    BetaClaudeCodeRbacGroupDefaultManagedSettings.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaClaudeCodeRbacGroupDefaultManagedSettings.settings())
            .contains(
                BetaClaudeCodeRbacGroupDefaultManagedSettings.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaClaudeCodeRbacGroupDefaultManagedSettings.updatedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupDefaultManagedSettings =
            BetaClaudeCodeRbacGroupDefaultManagedSettings.builder()
                .settings(
                    BetaClaudeCodeRbacGroupDefaultManagedSettings.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaClaudeCodeRbacGroupDefaultManagedSettings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupDefaultManagedSettings),
                jacksonTypeRef<BetaClaudeCodeRbacGroupDefaultManagedSettings>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupDefaultManagedSettings)
            .isEqualTo(betaClaudeCodeRbacGroupDefaultManagedSettings)
    }
}
