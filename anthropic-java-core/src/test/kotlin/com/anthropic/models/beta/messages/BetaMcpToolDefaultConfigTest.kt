// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolDefaultConfigTest {

    @Test
    fun create() {
        val betaMcpToolDefaultConfig =
            BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaMcpToolDefaultConfig.deferLoading()).contains(true)
        assertThat(betaMcpToolDefaultConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolDefaultConfig =
            BetaMcpToolDefaultConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaMcpToolDefaultConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolDefaultConfig),
                jacksonTypeRef<BetaMcpToolDefaultConfig>(),
            )

        assertThat(roundtrippedBetaMcpToolDefaultConfig).isEqualTo(betaMcpToolDefaultConfig)
    }
}
