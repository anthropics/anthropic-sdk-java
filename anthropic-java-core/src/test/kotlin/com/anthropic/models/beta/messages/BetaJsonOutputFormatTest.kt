// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaJsonOutputFormatTest {

    @Test
    fun create() {
        val betaJsonOutputFormat =
            BetaJsonOutputFormat.builder()
                .schema(
                    BetaJsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(betaJsonOutputFormat.schema())
            .isEqualTo(
                BetaJsonOutputFormat.Schema.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaJsonOutputFormat =
            BetaJsonOutputFormat.builder()
                .schema(
                    BetaJsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val roundtrippedBetaJsonOutputFormat =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaJsonOutputFormat),
                jacksonTypeRef<BetaJsonOutputFormat>(),
            )

        assertThat(roundtrippedBetaJsonOutputFormat).isEqualTo(betaJsonOutputFormat)
    }
}
