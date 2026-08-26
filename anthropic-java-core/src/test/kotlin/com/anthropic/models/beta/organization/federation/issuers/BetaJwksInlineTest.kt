// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaJwksInlineTest {

    @Test
    fun create() {
        val betaJwksInline =
            BetaJwksInline.builder()
                .addKey(
                    BetaJwksInline.Key.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(betaJwksInline.keys())
            .containsExactly(
                BetaJwksInline.Key.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaJwksInline =
            BetaJwksInline.builder()
                .addKey(
                    BetaJwksInline.Key.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val roundtrippedBetaJwksInline =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaJwksInline),
                jacksonTypeRef<BetaJwksInline>(),
            )

        assertThat(roundtrippedBetaJwksInline).isEqualTo(betaJwksInline)
    }
}
