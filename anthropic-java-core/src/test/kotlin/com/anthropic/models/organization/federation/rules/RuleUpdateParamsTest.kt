package com.anthropic.models.organization.federation.rules

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuleUpdateParamsTest {

    @Test
    fun create() {
        RuleUpdateParams.builder()
            .federationRuleId("federation_rule_id")
            .appliesToAllWorkspaces(true)
            .description("description")
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
    fun body() {
        val params =
            RuleUpdateParams.builder()
                .federationRuleId("federation_rule_id")
                .appliesToAllWorkspaces(true)
                .description("description")
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
                .tokenLifetimeSeconds(60L)
                .workspaceId("workspace_id")
                .build()

        val body = params._body()

        assertThat(body.appliesToAllWorkspaces()).contains(true)
        assertThat(body.description()).contains("description")
        assertThat(body.match())
            .contains(
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
        assertThat(body.name()).contains("x")
        assertThat(body.oauthScope()).contains("x")
        assertThat(body.target())
            .contains(
                ServiceAccountTarget.builder()
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
