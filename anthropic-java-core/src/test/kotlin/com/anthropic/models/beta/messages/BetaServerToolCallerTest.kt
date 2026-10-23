package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolCallerTest {

    @Test
    fun create() {
        val betaServerToolCaller = BetaServerToolCaller.of("srvtoolu_c")

        assertThat(betaServerToolCaller.toolId()).isEqualTo("srvtoolu_c")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolCaller = BetaServerToolCaller.of("srvtoolu_c")

        val roundtrippedBetaServerToolCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolCaller),
                jacksonTypeRef<BetaServerToolCaller>(),
            )

        assertThat(roundtrippedBetaServerToolCaller).isEqualTo(betaServerToolCaller)
    }
}
