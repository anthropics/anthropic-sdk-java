// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApiKeyCreatedByTest {

    @Test
    fun create() {
        val betaApiKeyCreatedBy =
            BetaApiKeyCreatedBy.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .type(BetaApiKeyCreatedBy.Type.USER)
                .build()

        assertThat(betaApiKeyCreatedBy.id()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(betaApiKeyCreatedBy.type()).isEqualTo(BetaApiKeyCreatedBy.Type.USER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApiKeyCreatedBy =
            BetaApiKeyCreatedBy.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .type(BetaApiKeyCreatedBy.Type.USER)
                .build()

        val roundtrippedBetaApiKeyCreatedBy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApiKeyCreatedBy),
                jacksonTypeRef<BetaApiKeyCreatedBy>(),
            )

        assertThat(roundtrippedBetaApiKeyCreatedBy).isEqualTo(betaApiKeyCreatedBy)
    }
}
