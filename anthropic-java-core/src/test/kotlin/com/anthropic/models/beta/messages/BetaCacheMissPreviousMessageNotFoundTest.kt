// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheMissPreviousMessageNotFoundTest {

    @Test
    fun create() {
        val betaCacheMissPreviousMessageNotFound =
            BetaCacheMissPreviousMessageNotFound.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissPreviousMessageNotFound =
            BetaCacheMissPreviousMessageNotFound.builder().build()

        val roundtrippedBetaCacheMissPreviousMessageNotFound =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissPreviousMessageNotFound),
                jacksonTypeRef<BetaCacheMissPreviousMessageNotFound>(),
            )

        assertThat(roundtrippedBetaCacheMissPreviousMessageNotFound)
            .isEqualTo(betaCacheMissPreviousMessageNotFound)
    }
}
