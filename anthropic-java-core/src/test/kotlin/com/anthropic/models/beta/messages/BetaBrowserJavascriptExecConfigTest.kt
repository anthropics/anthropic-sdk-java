// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserJavascriptExecConfigTest {

    @Test
    fun create() {
        val betaBrowserJavascriptExecConfig =
            BetaBrowserJavascriptExecConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserJavascriptExecConfig.deferLoading()).contains(true)
        assertThat(betaBrowserJavascriptExecConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserJavascriptExecConfig =
            BetaBrowserJavascriptExecConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserJavascriptExecConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserJavascriptExecConfig),
                jacksonTypeRef<BetaBrowserJavascriptExecConfig>(),
            )

        assertThat(roundtrippedBetaBrowserJavascriptExecConfig)
            .isEqualTo(betaBrowserJavascriptExecConfig)
    }
}
