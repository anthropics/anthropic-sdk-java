// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheControlEphemeralTest {

    @Test
    fun create() {
        val betaCacheControlEphemeral = BetaCacheControlEphemeral.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheControlEphemeral = BetaCacheControlEphemeral.builder().build()

        val roundtrippedBetaCacheControlEphemeral =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheControlEphemeral),
                jacksonTypeRef<BetaCacheControlEphemeral>(),
            )

        assertThat(roundtrippedBetaCacheControlEphemeral).isEqualTo(betaCacheControlEphemeral)
    }
}
