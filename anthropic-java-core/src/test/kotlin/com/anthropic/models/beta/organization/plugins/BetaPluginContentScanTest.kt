package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPluginContentScanTest {

    @Test
    fun create() {
        val betaPluginContentScan =
            BetaPluginContentScan.builder()
                .assessment(BetaPluginContentScan.Assessment.WARN)
                .reason("credential-exposure")
                .status(BetaPluginContentScan.Status.COMPLETED)
                .build()

        assertThat(betaPluginContentScan.assessment())
            .contains(BetaPluginContentScan.Assessment.WARN)
        assertThat(betaPluginContentScan.reason()).contains("credential-exposure")
        assertThat(betaPluginContentScan.status()).isEqualTo(BetaPluginContentScan.Status.COMPLETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPluginContentScan =
            BetaPluginContentScan.builder()
                .assessment(BetaPluginContentScan.Assessment.WARN)
                .reason("credential-exposure")
                .status(BetaPluginContentScan.Status.COMPLETED)
                .build()

        val roundtrippedBetaPluginContentScan =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPluginContentScan),
                jacksonTypeRef<BetaPluginContentScan>(),
            )

        assertThat(roundtrippedBetaPluginContentScan).isEqualTo(betaPluginContentScan)
    }
}
