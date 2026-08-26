// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.federation

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRuleMatch
import com.anthropic.models.beta.organization.federation.rules.BetaServiceAccountTarget
import com.anthropic.models.beta.organization.federation.rules.RuleArchiveParams
import com.anthropic.models.beta.organization.federation.rules.RuleCreateParams
import com.anthropic.models.beta.organization.federation.rules.RuleRetrieveParams
import com.anthropic.models.beta.organization.federation.rules.RuleUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RuleServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.beta().organization().federation().rules()

        val betaFederationRule =
            ruleService.create(
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
            )

        betaFederationRule.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.beta().organization().federation().rules()

        val betaFederationRule =
            ruleService.retrieve(
                RuleRetrieveParams.builder()
                    .federationRuleId("federation_rule_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaFederationRule.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.beta().organization().federation().rules()

        val betaFederationRule =
            ruleService.update(
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
            )

        betaFederationRule.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.beta().organization().federation().rules()

        val page = ruleService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.beta().organization().federation().rules()

        val betaFederationRule =
            ruleService.archive(
                RuleArchiveParams.builder()
                    .federationRuleId("federation_rule_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaFederationRule.validate()
    }
}
