// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFederationRuleTest {

    @Test
    fun create() {
        val betaFederationRule =
            BetaFederationRule.builder()
                .id("fdrl_01SDCCSbTxrXDpWc1phhtcfK")
                .appliesToAllWorkspaces(true)
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .attributes(
                    BetaFederationRule.Attributes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .description("description")
                .issuerId("issuer_id")
                .issuerName("issuer_name")
                .match(
                    BetaFederationRuleMatch.builder()
                        .audience("audience")
                        .claims(
                            BetaFederationRuleMatch.Claims.builder()
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
                    BetaServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .tokenLifetimeSeconds(0L)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .workspaceId("workspace_id")
                .addWorkspaceId("string")
                .build()

        assertThat(betaFederationRule.id()).isEqualTo("fdrl_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaFederationRule.appliesToAllWorkspaces()).isEqualTo(true)
        assertThat(betaFederationRule.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaFederationRule.archivedByActorId()).contains("archived_by_actor_id")
        assertThat(betaFederationRule.attributes())
            .contains(
                BetaFederationRule.Attributes.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(betaFederationRule.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaFederationRule.createdByActorId()).contains("created_by_actor_id")
        assertThat(betaFederationRule.description()).contains("description")
        assertThat(betaFederationRule.issuerId()).isEqualTo("issuer_id")
        assertThat(betaFederationRule.issuerName()).contains("issuer_name")
        assertThat(betaFederationRule.match())
            .isEqualTo(
                BetaFederationRuleMatch.builder()
                    .audience("audience")
                    .claims(
                        BetaFederationRuleMatch.Claims.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .condition("condition")
                    .subjectPrefix("subject_prefix")
                    .build()
            )
        assertThat(betaFederationRule.name()).isEqualTo("prod-deploy-pipeline")
        assertThat(betaFederationRule.oauthScope()).isEqualTo("oauth_scope")
        assertThat(betaFederationRule.target())
            .isEqualTo(
                BetaServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
        assertThat(betaFederationRule.tokenLifetimeSeconds()).isEqualTo(0L)
        assertThat(betaFederationRule.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaFederationRule.updatedByActorId()).contains("updated_by_actor_id")
        assertThat(betaFederationRule.workspaceId()).contains("workspace_id")
        assertThat(betaFederationRule.workspaceIds()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFederationRule =
            BetaFederationRule.builder()
                .id("fdrl_01SDCCSbTxrXDpWc1phhtcfK")
                .appliesToAllWorkspaces(true)
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .attributes(
                    BetaFederationRule.Attributes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .description("description")
                .issuerId("issuer_id")
                .issuerName("issuer_name")
                .match(
                    BetaFederationRuleMatch.builder()
                        .audience("audience")
                        .claims(
                            BetaFederationRuleMatch.Claims.builder()
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
                    BetaServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .tokenLifetimeSeconds(0L)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .workspaceId("workspace_id")
                .addWorkspaceId("string")
                .build()

        val roundtrippedBetaFederationRule =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFederationRule),
                jacksonTypeRef<BetaFederationRule>(),
            )

        assertThat(roundtrippedBetaFederationRule).isEqualTo(betaFederationRule)
    }
}
