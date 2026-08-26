// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFederationIssuerTest {

    @Test
    fun create() {
        val betaFederationIssuer =
            BetaFederationIssuer.builder()
                .id("fdis_01SDCCSbTxrXDpWc1phhtcfK")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .checkJti(true)
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .issuerUrl("https://token.actions.githubusercontent.com")
                .jwks(
                    BetaJwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .jwksPollingDisabledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .maxJwtLifetimeSeconds(0L)
                .name("github-actions")
                .pollStatus(
                    BetaFederationIssuerPollStatus.builder()
                        .consecutiveFailures(0L)
                        .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .build()

        assertThat(betaFederationIssuer.id()).isEqualTo("fdis_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaFederationIssuer.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaFederationIssuer.archivedByActorId()).contains("archived_by_actor_id")
        assertThat(betaFederationIssuer.checkJti()).isEqualTo(true)
        assertThat(betaFederationIssuer.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaFederationIssuer.createdByActorId()).contains("created_by_actor_id")
        assertThat(betaFederationIssuer.issuerUrl())
            .isEqualTo("https://token.actions.githubusercontent.com")
        assertThat(betaFederationIssuer.jwks())
            .isEqualTo(
                BetaFederationIssuer.Jwks.ofDiscovery(
                    BetaJwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
            )
        assertThat(betaFederationIssuer.jwksPollingDisabledAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaFederationIssuer.maxJwtLifetimeSeconds()).isEqualTo(0L)
        assertThat(betaFederationIssuer.name()).isEqualTo("github-actions")
        assertThat(betaFederationIssuer.pollStatus())
            .contains(
                BetaFederationIssuerPollStatus.builder()
                    .consecutiveFailures(0L)
                    .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(betaFederationIssuer.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaFederationIssuer.updatedByActorId()).contains("updated_by_actor_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFederationIssuer =
            BetaFederationIssuer.builder()
                .id("fdis_01SDCCSbTxrXDpWc1phhtcfK")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .checkJti(true)
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .issuerUrl("https://token.actions.githubusercontent.com")
                .jwks(
                    BetaJwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .jwksPollingDisabledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .maxJwtLifetimeSeconds(0L)
                .name("github-actions")
                .pollStatus(
                    BetaFederationIssuerPollStatus.builder()
                        .consecutiveFailures(0L)
                        .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .build()

        val roundtrippedBetaFederationIssuer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFederationIssuer),
                jacksonTypeRef<BetaFederationIssuer>(),
            )

        assertThat(roundtrippedBetaFederationIssuer).isEqualTo(betaFederationIssuer)
    }
}
