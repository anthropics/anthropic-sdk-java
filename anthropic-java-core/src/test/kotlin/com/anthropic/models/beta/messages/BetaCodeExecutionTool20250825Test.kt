// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionTool20250825Test {

    @Test
    fun create() {
        val betaCodeExecutionTool20250825 =
            BetaCodeExecutionTool20250825.builder()
                .addAllowedCaller(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(betaCodeExecutionTool20250825.allowedCallers().getOrNull())
            .containsExactly(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
        assertThat(betaCodeExecutionTool20250825.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaCodeExecutionTool20250825.deferLoading()).contains(true)
        assertThat(betaCodeExecutionTool20250825.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionTool20250825 =
            BetaCodeExecutionTool20250825.builder()
                .addAllowedCaller(BetaCodeExecutionTool20250825.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedBetaCodeExecutionTool20250825 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionTool20250825),
                jacksonTypeRef<BetaCodeExecutionTool20250825>(),
            )

        assertThat(roundtrippedBetaCodeExecutionTool20250825)
            .isEqualTo(betaCodeExecutionTool20250825)
    }
}
