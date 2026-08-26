// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleCreateParamsTest {

    @Test
    fun create() {
        RuleCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .issuerId("issuer_id")
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
            .name("x")
            .oauthScope("x")
            .target(
                BetaServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
            .appliesToAllWorkspaces(true)
            .attributes(
                RuleCreateParams.Attributes.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
            .description("description")
            .tokenLifetimeSeconds(60L)
            .workspaceId("workspace_id")
            .build()
    }

    @Test
    fun headers() {
        val params =
            RuleCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .issuerId("issuer_id")
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
                .name("x")
                .oauthScope("x")
                .target(
                    BetaServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .appliesToAllWorkspaces(true)
                .attributes(
                    RuleCreateParams.Attributes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .description("description")
                .tokenLifetimeSeconds(60L)
                .workspaceId("workspace_id")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            RuleCreateParams.builder()
                .issuerId("issuer_id")
                .match(BetaFederationRuleMatch.builder().build())
                .name("x")
                .oauthScope("x")
                .target(BetaServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            RuleCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .issuerId("issuer_id")
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
                .name("x")
                .oauthScope("x")
                .target(
                    BetaServiceAccountTarget.builder()
                        .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                        .serviceAccountName("service_account_name")
                        .build()
                )
                .appliesToAllWorkspaces(true)
                .attributes(
                    RuleCreateParams.Attributes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .description("description")
                .tokenLifetimeSeconds(60L)
                .workspaceId("workspace_id")
                .build()

        val body = params._body()

        assertThat(body.issuerId()).isEqualTo("issuer_id")
        assertThat(body.match())
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
        assertThat(body.name()).isEqualTo("x")
        assertThat(body.oauthScope()).isEqualTo("x")
        assertThat(body.target())
            .isEqualTo(
                BetaServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
        assertThat(body.appliesToAllWorkspaces()).contains(true)
        assertThat(body.attributes())
            .contains(
                RuleCreateParams.Attributes.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.description()).contains("description")
        assertThat(body.tokenLifetimeSeconds()).contains(60L)
        assertThat(body.workspaceId()).contains("workspace_id")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            RuleCreateParams.builder()
                .issuerId("issuer_id")
                .match(BetaFederationRuleMatch.builder().build())
                .name("x")
                .oauthScope("x")
                .target(BetaServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
                .build()

        val body = params._body()

        assertThat(body.issuerId()).isEqualTo("issuer_id")
        assertThat(body.match()).isEqualTo(BetaFederationRuleMatch.builder().build())
        assertThat(body.name()).isEqualTo("x")
        assertThat(body.oauthScope()).isEqualTo("x")
        assertThat(body.target())
            .isEqualTo(BetaServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
    }
}
