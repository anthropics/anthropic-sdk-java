// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20241022Test {

    @Test
    fun create() {
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .addAllowedCaller(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaToolTextEditor20241022.allowedCallers().getOrNull())
            .containsExactly(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
        assertThat(betaToolTextEditor20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolTextEditor20241022.deferLoading()).contains(true)
        assertThat(betaToolTextEditor20241022.inputExamples().getOrNull())
            .containsExactly(
                BetaToolTextEditor20241022.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolTextEditor20241022.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20241022 =
            BetaToolTextEditor20241022.builder()
                .addAllowedCaller(BetaToolTextEditor20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaToolTextEditor20241022 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20241022),
                jacksonTypeRef<BetaToolTextEditor20241022>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20241022).isEqualTo(betaToolTextEditor20241022)
    }
}
