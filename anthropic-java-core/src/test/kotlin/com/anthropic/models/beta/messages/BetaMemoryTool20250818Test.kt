// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818Test {

    @Test
    fun create() {
        val betaMemoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .addAllowedCaller(BetaMemoryTool20250818.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaMemoryTool20250818.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaMemoryTool20250818.allowedCallers().getOrNull())
            .containsExactly(BetaMemoryTool20250818.AllowedCaller.DIRECT)
        assertThat(betaMemoryTool20250818.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaMemoryTool20250818.deferLoading()).contains(true)
        assertThat(betaMemoryTool20250818.inputExamples().getOrNull())
            .containsExactly(
                BetaMemoryTool20250818.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaMemoryTool20250818.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .addAllowedCaller(BetaMemoryTool20250818.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaMemoryTool20250818.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaMemoryTool20250818 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818),
                jacksonTypeRef<BetaMemoryTool20250818>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818).isEqualTo(betaMemoryTool20250818)
    }
}
