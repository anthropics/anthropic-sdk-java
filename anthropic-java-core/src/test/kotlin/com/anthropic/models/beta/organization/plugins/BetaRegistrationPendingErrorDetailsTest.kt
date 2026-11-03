package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRegistrationPendingErrorDetailsTest {

    @Test
    fun create() {
        val betaRegistrationPendingErrorDetails =
            BetaRegistrationPendingErrorDetails.builder()
                .pluginId("plugin_id")
                .pluginVersionId("plugin_version_id")
                .build()

        assertThat(betaRegistrationPendingErrorDetails.pluginId()).isEqualTo("plugin_id")
        assertThat(betaRegistrationPendingErrorDetails.pluginVersionId())
            .isEqualTo("plugin_version_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRegistrationPendingErrorDetails =
            BetaRegistrationPendingErrorDetails.builder()
                .pluginId("plugin_id")
                .pluginVersionId("plugin_version_id")
                .build()

        val roundtrippedBetaRegistrationPendingErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRegistrationPendingErrorDetails),
                jacksonTypeRef<BetaRegistrationPendingErrorDetails>(),
            )

        assertThat(roundtrippedBetaRegistrationPendingErrorDetails)
            .isEqualTo(betaRegistrationPendingErrorDetails)
    }
}
