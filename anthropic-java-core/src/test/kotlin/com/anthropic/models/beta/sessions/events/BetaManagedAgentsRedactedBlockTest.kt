// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsRedactedBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsRedactedBlock =
            BetaManagedAgentsRedactedBlock.of(BetaManagedAgentsRedactedBlock.Type.REDACTED)

        assertThat(betaManagedAgentsRedactedBlock.type())
            .isEqualTo(BetaManagedAgentsRedactedBlock.Type.REDACTED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsRedactedBlock =
            BetaManagedAgentsRedactedBlock.of(BetaManagedAgentsRedactedBlock.Type.REDACTED)

        val roundtrippedBetaManagedAgentsRedactedBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsRedactedBlock),
                jacksonTypeRef<BetaManagedAgentsRedactedBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsRedactedBlock)
            .isEqualTo(betaManagedAgentsRedactedBlock)
    }
}
