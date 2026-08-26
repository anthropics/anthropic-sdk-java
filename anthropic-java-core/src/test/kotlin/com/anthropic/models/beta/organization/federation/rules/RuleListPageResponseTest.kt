// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleListPageResponseTest {

    @Test
    fun create() {
        val ruleListPageResponse =
            RuleListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(ruleListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(ruleListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val ruleListPageResponse =
            RuleListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedRuleListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(ruleListPageResponse),
                jacksonTypeRef<RuleListPageResponse>(),
            )

        assertThat(roundtrippedRuleListPageResponse).isEqualTo(ruleListPageResponse)
    }
}
