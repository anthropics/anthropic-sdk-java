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
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .issuerId("fdis_01SDCCSbTxrXDpWc1phhtcfK")
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
            .oauthScope("workspace:developer")
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
            .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
            .build()
    }

    @Test
    fun headers() {
        val params =
            RuleCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .issuerId("fdis_01SDCCSbTxrXDpWc1phhtcfK")
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
                .oauthScope("workspace:developer")
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
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            RuleCreateParams.builder()
                .issuerId("fdis_01SDCCSbTxrXDpWc1phhtcfK")
                .match(BetaFederationRuleMatch.builder().build())
                .name("prod-deploy-pipeline")
                .oauthScope("workspace:developer")
                .target(BetaServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            RuleCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .issuerId("fdis_01SDCCSbTxrXDpWc1phhtcfK")
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
                .oauthScope("workspace:developer")
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
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        val body = params._body()

        assertThat(body.issuerId()).isEqualTo("fdis_01SDCCSbTxrXDpWc1phhtcfK")
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
        assertThat(body.name()).isEqualTo("prod-deploy-pipeline")
        assertThat(body.oauthScope()).isEqualTo("workspace:developer")
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
        assertThat(body.workspaceId()).contains("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            RuleCreateParams.builder()
                .issuerId("fdis_01SDCCSbTxrXDpWc1phhtcfK")
                .match(BetaFederationRuleMatch.builder().build())
                .name("prod-deploy-pipeline")
                .oauthScope("workspace:developer")
                .target(BetaServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
                .build()

        val body = params._body()

        assertThat(body.issuerId()).isEqualTo("fdis_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(body.match()).isEqualTo(BetaFederationRuleMatch.builder().build())
        assertThat(body.name()).isEqualTo("prod-deploy-pipeline")
        assertThat(body.oauthScope()).isEqualTo("workspace:developer")
        assertThat(body.target())
            .isEqualTo(BetaServiceAccountTarget.of("svac_01SDCCSbTxrXDpWc1phhtcfK"))
    }
}
