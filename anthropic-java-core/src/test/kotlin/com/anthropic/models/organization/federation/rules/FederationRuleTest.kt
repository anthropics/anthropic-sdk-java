package com.anthropic.models.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FederationRuleTest {

    @Test
    fun create() {
        val federationRule =
            FederationRule.builder()
                .id("fdrl_01SDCCSbTxrXDpWc1phhtcfK")
                .appliesToAllWorkspaces(true)
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .description("description")
                .issuerId("issuer_id")
                .issuerName("issuer_name")
                .match(
                    FederationRuleMatch.builder()
                        .audience("audience")
                        .claims(
                            FederationRuleMatch.Claims.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .condition("condition")
                        .subjectPrefix("subject_prefix")
                        .build()
                )
                .name("prod-deploy-pipeline")
                .oauthScope("oauth_scope")
                .target(
                    ServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .tokenLifetimeSeconds(0L)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .addWorkspaceId("string")
                .build()

        assertThat(federationRule.id()).isEqualTo("fdrl_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(federationRule.appliesToAllWorkspaces()).isEqualTo(true)
        assertThat(federationRule.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(federationRule.archivedByActorId()).contains("archived_by_actor_id")
        assertThat(federationRule.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(federationRule.createdByActorId()).contains("created_by_actor_id")
        assertThat(federationRule.description()).contains("description")
        assertThat(federationRule.issuerId()).isEqualTo("issuer_id")
        assertThat(federationRule.issuerName()).contains("issuer_name")
        assertThat(federationRule.match())
            .isEqualTo(
                FederationRuleMatch.builder()
                    .audience("audience")
                    .claims(
                        FederationRuleMatch.Claims.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .condition("condition")
                    .subjectPrefix("subject_prefix")
                    .build()
            )
        assertThat(federationRule.name()).isEqualTo("prod-deploy-pipeline")
        assertThat(federationRule.oauthScope()).isEqualTo("oauth_scope")
        assertThat(federationRule.target())
            .isEqualTo(
                ServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
        assertThat(federationRule.tokenLifetimeSeconds()).isEqualTo(0L)
        assertThat(federationRule.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(federationRule.updatedByActorId()).contains("updated_by_actor_id")
        assertThat(federationRule.workspaceIds()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val federationRule =
            FederationRule.builder()
                .id("fdrl_01SDCCSbTxrXDpWc1phhtcfK")
                .appliesToAllWorkspaces(true)
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .description("description")
                .issuerId("issuer_id")
                .issuerName("issuer_name")
                .match(
                    FederationRuleMatch.builder()
                        .audience("audience")
                        .claims(
                            FederationRuleMatch.Claims.builder()
                                .putAdditionalProperty("foo", JsonValue.from("string"))
                                .build()
                        )
                        .condition("condition")
                        .subjectPrefix("subject_prefix")
                        .build()
                )
                .name("prod-deploy-pipeline")
                .oauthScope("oauth_scope")
                .target(
                    ServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .tokenLifetimeSeconds(0L)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .addWorkspaceId("string")
                .build()

        val roundtrippedFederationRule =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(federationRule),
                jacksonTypeRef<FederationRule>(),
            )

        assertThat(roundtrippedFederationRule).isEqualTo(federationRule)
    }
}
