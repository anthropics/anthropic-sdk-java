package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaScanFailedErrorDetailsTest {

    @Test
    fun create() {
        val betaScanFailedErrorDetails = BetaScanFailedErrorDetails.of("plugin_version_id")

        assertThat(betaScanFailedErrorDetails.pluginVersionId()).isEqualTo("plugin_version_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaScanFailedErrorDetails = BetaScanFailedErrorDetails.of("plugin_version_id")

        val roundtrippedBetaScanFailedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaScanFailedErrorDetails),
                jacksonTypeRef<BetaScanFailedErrorDetails>(),
            )

        assertThat(roundtrippedBetaScanFailedErrorDetails).isEqualTo(betaScanFailedErrorDetails)
    }
}
