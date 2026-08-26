// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFederationIssuerPollStatusTest {

    @Test
    fun create() {
        val betaFederationIssuerPollStatus =
            BetaFederationIssuerPollStatus.builder()
                .consecutiveFailures(0L)
                .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaFederationIssuerPollStatus.consecutiveFailures()).isEqualTo(0L)
        assertThat(betaFederationIssuerPollStatus.lastFetchedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaFederationIssuerPollStatus.nextPollAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFederationIssuerPollStatus =
            BetaFederationIssuerPollStatus.builder()
                .consecutiveFailures(0L)
                .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaFederationIssuerPollStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFederationIssuerPollStatus),
                jacksonTypeRef<BetaFederationIssuerPollStatus>(),
            )

        assertThat(roundtrippedBetaFederationIssuerPollStatus)
            .isEqualTo(betaFederationIssuerPollStatus)
    }
}
