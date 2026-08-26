// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IssuerListPageResponseTest {

    @Test
    fun create() {
        val issuerListPageResponse =
            IssuerListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(issuerListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(issuerListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val issuerListPageResponse =
            IssuerListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedIssuerListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(issuerListPageResponse),
                jacksonTypeRef<IssuerListPageResponse>(),
            )

        assertThat(roundtrippedIssuerListPageResponse).isEqualTo(issuerListPageResponse)
    }
}
