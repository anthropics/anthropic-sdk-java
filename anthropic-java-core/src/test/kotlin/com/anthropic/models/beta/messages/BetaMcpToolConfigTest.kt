// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolConfigTest {

    @Test
    fun create() {
        val betaMcpToolConfig = BetaMcpToolConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaMcpToolConfig.deferLoading()).contains(true)
        assertThat(betaMcpToolConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolConfig = BetaMcpToolConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaMcpToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolConfig),
                jacksonTypeRef<BetaMcpToolConfig>(),
            )

        assertThat(roundtrippedBetaMcpToolConfig).isEqualTo(betaMcpToolConfig)
    }
}
