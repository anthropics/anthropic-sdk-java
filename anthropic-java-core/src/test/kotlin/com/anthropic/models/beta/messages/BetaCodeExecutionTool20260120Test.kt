// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionTool20260120Test {

    @Test
    fun create() {
        val betaCodeExecutionTool20260120 =
            BetaCodeExecutionTool20260120.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(betaCodeExecutionTool20260120.allowedCallers().getOrNull())
            .containsExactly(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
        assertThat(betaCodeExecutionTool20260120.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaCodeExecutionTool20260120.deferLoading()).contains(true)
        assertThat(betaCodeExecutionTool20260120.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionTool20260120 =
            BetaCodeExecutionTool20260120.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260120.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedBetaCodeExecutionTool20260120 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionTool20260120),
                jacksonTypeRef<BetaCodeExecutionTool20260120>(),
            )

        assertThat(roundtrippedBetaCodeExecutionTool20260120)
            .isEqualTo(betaCodeExecutionTool20260120)
    }
}
