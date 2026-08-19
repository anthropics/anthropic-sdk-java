// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserDoubleClickConfigTest {

    @Test
    fun create() {
        val betaBrowserDoubleClickConfig =
            BetaBrowserDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserDoubleClickConfig.deferLoading()).contains(true)
        assertThat(betaBrowserDoubleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserDoubleClickConfig =
            BetaBrowserDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserDoubleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserDoubleClickConfig),
                jacksonTypeRef<BetaBrowserDoubleClickConfig>(),
            )

        assertThat(roundtrippedBetaBrowserDoubleClickConfig).isEqualTo(betaBrowserDoubleClickConfig)
    }
}
