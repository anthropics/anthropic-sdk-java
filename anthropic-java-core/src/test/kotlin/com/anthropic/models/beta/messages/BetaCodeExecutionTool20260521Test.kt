// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionTool20260521Test {

    @Test
    fun create() {
        val betaCodeExecutionTool20260521 =
            BetaCodeExecutionTool20260521.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(betaCodeExecutionTool20260521.allowedCallers().getOrNull())
            .containsExactly(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
        assertThat(betaCodeExecutionTool20260521.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaCodeExecutionTool20260521.deferLoading()).contains(true)
        assertThat(betaCodeExecutionTool20260521.strict()).contains(true)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaCodeExecutionTool20260521 = BetaCodeExecutionTool20260521.builder().build()

        val betaCodeExecutionTool20260521 =
            baseBetaCodeExecutionTool20260521
                .toBuilder()
                .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                .build()

        assertThat(betaCodeExecutionTool20260521.allowedCallers().getOrNull())
            .containsExactly(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionTool20260521 =
            BetaCodeExecutionTool20260521.builder()
                .addAllowedCaller(BetaCodeExecutionTool20260521.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedBetaCodeExecutionTool20260521 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionTool20260521),
                jacksonTypeRef<BetaCodeExecutionTool20260521>(),
            )

        assertThat(roundtrippedBetaCodeExecutionTool20260521)
            .isEqualTo(betaCodeExecutionTool20260521)
    }
}
