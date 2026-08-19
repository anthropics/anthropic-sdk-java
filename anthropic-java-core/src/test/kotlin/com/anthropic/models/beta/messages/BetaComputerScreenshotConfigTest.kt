// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerScreenshotConfigTest {

    @Test
    fun create() {
        val betaComputerScreenshotConfig =
            BetaComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerScreenshotConfig.deferLoading()).contains(true)
        assertThat(betaComputerScreenshotConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerScreenshotConfig =
            BetaComputerScreenshotConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerScreenshotConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerScreenshotConfig),
                jacksonTypeRef<BetaComputerScreenshotConfig>(),
            )

        assertThat(roundtrippedBetaComputerScreenshotConfig).isEqualTo(betaComputerScreenshotConfig)
    }
}
