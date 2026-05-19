// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedWorkUpdateRequestTest {

    @Test
    fun create() {
        val betaSelfHostedWorkUpdateRequest =
            BetaSelfHostedWorkUpdateRequest.builder()
                .metadata(
                    BetaSelfHostedWorkUpdateRequest.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        assertThat(betaSelfHostedWorkUpdateRequest.metadata())
            .isEqualTo(
                BetaSelfHostedWorkUpdateRequest.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedWorkUpdateRequest =
            BetaSelfHostedWorkUpdateRequest.builder()
                .metadata(
                    BetaSelfHostedWorkUpdateRequest.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .build()

        val roundtrippedBetaSelfHostedWorkUpdateRequest =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedWorkUpdateRequest),
                jacksonTypeRef<BetaSelfHostedWorkUpdateRequest>(),
            )

        assertThat(roundtrippedBetaSelfHostedWorkUpdateRequest)
            .isEqualTo(betaSelfHostedWorkUpdateRequest)
    }
}
