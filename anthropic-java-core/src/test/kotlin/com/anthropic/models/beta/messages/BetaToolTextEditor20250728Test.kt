// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20250728Test {

    @Test
    fun create() {
        val betaToolTextEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .addAllowedCaller(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        assertThat(betaToolTextEditor20250728.allowedCallers().getOrNull())
            .containsExactly(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
        assertThat(betaToolTextEditor20250728.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolTextEditor20250728.deferLoading()).contains(true)
        assertThat(betaToolTextEditor20250728.inputExamples().getOrNull())
            .containsExactly(
                BetaToolTextEditor20250728.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolTextEditor20250728.maxCharacters()).contains(1L)
        assertThat(betaToolTextEditor20250728.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .addAllowedCaller(BetaToolTextEditor20250728.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250728.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val roundtrippedBetaToolTextEditor20250728 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20250728),
                jacksonTypeRef<BetaToolTextEditor20250728>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20250728).isEqualTo(betaToolTextEditor20250728)
    }
}
