// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleUpdateParamsTest {

    @Test
    fun create() {
        RuleUpdateParams.builder()
            .federationRuleId("federation_rule_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .appliesToAllWorkspaces(true)
            .attributes(
                RuleUpdateParams.Attributes.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
            .description("description")
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
            .tokenLifetimeSeconds(60L)
            .workspaceId("workspace_id")
            .build()
    }

    @Test
    fun pathParams() {
        val params = RuleUpdateParams.builder().federationRuleId("federation_rule_id").build()

        assertThat(params._pathParam(0)).isEqualTo("federation_rule_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            RuleUpdateParams.builder()
                .federationRuleId("federation_rule_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .appliesToAllWorkspaces(true)
                .attributes(
                    RuleUpdateParams.Attributes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .description("description")
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
        val params = RuleUpdateParams.builder().federationRuleId("federation_rule_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            RuleUpdateParams.builder()
                .federationRuleId("federation_rule_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .appliesToAllWorkspaces(true)
                .attributes(
                    RuleUpdateParams.Attributes.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .description("description")
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
                .tokenLifetimeSeconds(60L)
                .workspaceId("workspace_id")
                .build()

        val body = params._body()

        assertThat(body.appliesToAllWorkspaces()).contains(true)
        assertThat(body.attributes())
            .contains(
                RuleUpdateParams.Attributes.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.description()).contains("description")
        assertThat(body.match())
            .contains(
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
        assertThat(body.name()).contains("x")
        assertThat(body.oauthScope()).contains("x")
        assertThat(body.target())
            .contains(
                BetaServiceAccountTarget.builder()
                    .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                    .serviceAccountName("service_account_name")
                    .build()
            )
        assertThat(body.tokenLifetimeSeconds()).contains(60L)
        assertThat(body.workspaceId()).contains("workspace_id")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = RuleUpdateParams.builder().federationRuleId("federation_rule_id").build()

        val body = params._body()
    }
}
