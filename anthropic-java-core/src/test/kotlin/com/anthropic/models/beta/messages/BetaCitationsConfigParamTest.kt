// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationsConfigParamTest {

    @Test
    fun create() {
        val betaCitationsConfigParam = BetaCitationsConfigParam.builder().enabled(true).build()

        assertThat(betaCitationsConfigParam.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationsConfigParam = BetaCitationsConfigParam.builder().enabled(true).build()

        val roundtrippedBetaCitationsConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationsConfigParam),
                jacksonTypeRef<BetaCitationsConfigParam>(),
            )

        assertThat(roundtrippedBetaCitationsConfigParam).isEqualTo(betaCitationsConfigParam)
    }
}
