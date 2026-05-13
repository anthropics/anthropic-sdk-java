// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheMissUnavailableTest {

    @Test
    fun create() {
        val betaCacheMissUnavailable = BetaCacheMissUnavailable.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissUnavailable = BetaCacheMissUnavailable.builder().build()

        val roundtrippedBetaCacheMissUnavailable =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissUnavailable),
                jacksonTypeRef<BetaCacheMissUnavailable>(),
            )

        assertThat(roundtrippedBetaCacheMissUnavailable).isEqualTo(betaCacheMissUnavailable)
    }
}
