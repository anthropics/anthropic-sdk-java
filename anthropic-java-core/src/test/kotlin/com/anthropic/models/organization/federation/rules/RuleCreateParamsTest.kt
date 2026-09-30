package com.anthropic.models.organization.federation.rules

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleCreateParamsTest {

    @Test
    fun create() {
        RuleCreateParams.builder()
            .issuerId("issuer_id")
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
            .name("x")
            .oauthScope("x")
            .target(
                ServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
            .appliesToAllWorkspaces(true)
            .description("description")
            .tokenLifetimeSeconds(60L)
            .workspaceId("workspace_id")
            .build()
    }

    @Test
    fun body() {
        val params =
            RuleCreateParams.builder()
                .issuerId("issuer_id")
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
                .name("x")
                .oauthScope("x")
                .target(
                    ServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .appliesToAllWorkspaces(true)
                .description("description")
                .tokenLifetimeSeconds(60L)
                .workspaceId("workspace_id")
                .build()

        val body = params._body()

        assertThat(body.issuerId()).isEqualTo("issuer_id")
        assertThat(body.match())
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
        assertThat(body.name()).isEqualTo("x")
        assertThat(body.oauthScope()).isEqualTo("x")
        assertThat(body.target())
            .isEqualTo(
                ServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
        assertThat(body.appliesToAllWorkspaces()).contains(true)
        assertThat(body.description()).contains("description")
        assertThat(body.tokenLifetimeSeconds()).contains(60L)
        assertThat(body.workspaceId()).contains("workspace_id")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            RuleCreateParams.builder()
                .issuerId("issuer_id")
                .match(FederationRuleMatch.builder().build())
                .name("x")
                .oauthScope("x")
                .target(ServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
                .build()

        val body = params._body()

        assertThat(body.issuerId()).isEqualTo("issuer_id")
        assertThat(body.match()).isEqualTo(FederationRuleMatch.builder().build())
        assertThat(body.name()).isEqualTo("x")
        assertThat(body.oauthScope()).isEqualTo("x")
        assertThat(body.target())
            .isEqualTo(ServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
    }
}
