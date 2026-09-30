package com.anthropic.models.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FederationIssuerPollStatusTest {

    @Test
    fun create() {
        val federationIssuerPollStatus =
            FederationIssuerPollStatus.builder()
                .consecutiveFailures(0L)
                .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(federationIssuerPollStatus.consecutiveFailures()).isEqualTo(0L)
        assertThat(federationIssuerPollStatus.lastFetchedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(federationIssuerPollStatus.nextPollAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val federationIssuerPollStatus =
            FederationIssuerPollStatus.builder()
                .consecutiveFailures(0L)
                .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedFederationIssuerPollStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(federationIssuerPollStatus),
                jacksonTypeRef<FederationIssuerPollStatus>(),
            )

        assertThat(roundtrippedFederationIssuerPollStatus).isEqualTo(federationIssuerPollStatus)
    }
}
