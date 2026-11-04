package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaScanPendingErrorDetailsTest {

    @Test
    fun create() {
        val betaScanPendingErrorDetails = BetaScanPendingErrorDetails.of("plugin_version_id")

        assertThat(betaScanPendingErrorDetails.pluginVersionId()).isEqualTo("plugin_version_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaScanPendingErrorDetails = BetaScanPendingErrorDetails.of("plugin_version_id")

        val roundtrippedBetaScanPendingErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaScanPendingErrorDetails),
                jacksonTypeRef<BetaScanPendingErrorDetails>(),
            )

        assertThat(roundtrippedBetaScanPendingErrorDetails).isEqualTo(betaScanPendingErrorDetails)
    }
}
