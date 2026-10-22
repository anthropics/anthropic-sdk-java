package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThreadContinueParamsTest {

    @Test
    fun create() {
        val betaThreadContinueParams = BetaThreadContinueParams.of("previous_message_id")

        assertThat(betaThreadContinueParams.previousMessageId()).isEqualTo("previous_message_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThreadContinueParams = BetaThreadContinueParams.of("previous_message_id")

        val roundtrippedBetaThreadContinueParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThreadContinueParams),
                jacksonTypeRef<BetaThreadContinueParams>(),
            )

        assertThat(roundtrippedBetaThreadContinueParams).isEqualTo(betaThreadContinueParams)
    }
}
