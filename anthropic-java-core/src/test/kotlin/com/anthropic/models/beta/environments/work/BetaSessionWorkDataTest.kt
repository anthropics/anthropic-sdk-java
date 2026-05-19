// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSessionWorkDataTest {

    @Test
    fun create() {
        val betaSessionWorkData = BetaSessionWorkData.builder().id("id").build()

        assertThat(betaSessionWorkData.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSessionWorkData = BetaSessionWorkData.builder().id("id").build()

        val roundtrippedBetaSessionWorkData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSessionWorkData),
                jacksonTypeRef<BetaSessionWorkData>(),
            )

        assertThat(roundtrippedBetaSessionWorkData).isEqualTo(betaSessionWorkData)
    }
}
