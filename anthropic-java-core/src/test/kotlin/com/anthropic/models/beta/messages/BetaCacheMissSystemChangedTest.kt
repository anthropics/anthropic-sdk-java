// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCacheMissSystemChangedTest {

    @Test
    fun create() {
        val betaCacheMissSystemChanged = BetaCacheMissSystemChanged.of(0L)

        assertThat(betaCacheMissSystemChanged.cacheMissedInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCacheMissSystemChanged = BetaCacheMissSystemChanged.of(0L)

        val roundtrippedBetaCacheMissSystemChanged =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCacheMissSystemChanged),
                jacksonTypeRef<BetaCacheMissSystemChanged>(),
            )

        assertThat(roundtrippedBetaCacheMissSystemChanged).isEqualTo(betaCacheMissSystemChanged)
    }
}
