// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScrollConfigTest {

    @Test
    fun create() {
        val betaBrowserScrollConfig =
            BetaBrowserScrollConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserScrollConfig.deferLoading()).contains(true)
        assertThat(betaBrowserScrollConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScrollConfig =
            BetaBrowserScrollConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserScrollConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScrollConfig),
                jacksonTypeRef<BetaBrowserScrollConfig>(),
            )

        assertThat(roundtrippedBetaBrowserScrollConfig).isEqualTo(betaBrowserScrollConfig)
    }
}
