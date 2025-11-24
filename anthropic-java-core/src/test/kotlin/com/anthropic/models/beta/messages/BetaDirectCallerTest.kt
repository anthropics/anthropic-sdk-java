// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDirectCallerTest {

    @Test
    fun create() {
        val betaDirectCaller = BetaDirectCaller.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDirectCaller = BetaDirectCaller.builder().build()

        val roundtrippedBetaDirectCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDirectCaller),
                jacksonTypeRef<BetaDirectCaller>(),
            )

        assertThat(roundtrippedBetaDirectCaller).isEqualTo(betaDirectCaller)
    }
}
