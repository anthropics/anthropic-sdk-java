package com.anthropic.models.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FederationIssuerTest {

    @Test
    fun create() {
        val federationIssuer =
            FederationIssuer.builder()
                .id("fdis_01SDCCSbTxrXDpWc1phhtcfK")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .checkJti(true)
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .issuerUrl("https://token.actions.githubusercontent.com")
                .jwks(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .jwksPollingDisabledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .maxJwtLifetimeSeconds(0L)
                .name("github-actions")
                .pollStatus(
                    FederationIssuerPollStatus.builder()
                        .consecutiveFailures(0L)
                        .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .build()

        assertThat(federationIssuer.id()).isEqualTo("fdis_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(federationIssuer.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(federationIssuer.archivedByActorId()).contains("archived_by_actor_id")
        assertThat(federationIssuer.checkJti()).isEqualTo(true)
        assertThat(federationIssuer.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(federationIssuer.createdByActorId()).contains("created_by_actor_id")
        assertThat(federationIssuer.issuerUrl())
            .isEqualTo("https://token.actions.githubusercontent.com")
        assertThat(federationIssuer.jwks())
            .isEqualTo(
                FederationIssuer.Jwks.ofDiscovery(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
            )
        assertThat(federationIssuer.jwksPollingDisabledAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(federationIssuer.maxJwtLifetimeSeconds()).isEqualTo(0L)
        assertThat(federationIssuer.name()).isEqualTo("github-actions")
        assertThat(federationIssuer.pollStatus())
            .contains(
                FederationIssuerPollStatus.builder()
                    .consecutiveFailures(0L)
                    .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(federationIssuer.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(federationIssuer.updatedByActorId()).contains("updated_by_actor_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val federationIssuer =
            FederationIssuer.builder()
                .id("fdis_01SDCCSbTxrXDpWc1phhtcfK")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .checkJti(true)
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .issuerUrl("https://token.actions.githubusercontent.com")
                .jwks(
                    JwksDiscovery.builder()
                        .caCertPem("ca_cert_pem")
                        .discoveryBase("discovery_base")
                        .build()
                )
                .jwksPollingDisabledAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .maxJwtLifetimeSeconds(0L)
                .name("github-actions")
                .pollStatus(
                    FederationIssuerPollStatus.builder()
                        .consecutiveFailures(0L)
                        .lastFetchedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .nextPollAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .build()

        val roundtrippedFederationIssuer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(federationIssuer),
                jacksonTypeRef<FederationIssuer>(),
            )

        assertThat(roundtrippedFederationIssuer).isEqualTo(federationIssuer)
    }
}
