// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFormInputConfigTest {

    @Test
    fun create() {
        val betaBrowserFormInputConfig =
            BetaBrowserFormInputConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserFormInputConfig.deferLoading()).contains(true)
        assertThat(betaBrowserFormInputConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFormInputConfig =
            BetaBrowserFormInputConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserFormInputConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFormInputConfig),
                jacksonTypeRef<BetaBrowserFormInputConfig>(),
            )

        assertThat(roundtrippedBetaBrowserFormInputConfig).isEqualTo(betaBrowserFormInputConfig)
    }
}
