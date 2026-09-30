package com.anthropic.services.blocking.organization.federation

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.organization.federation.rules.FederationRuleMatch
import com.anthropic.models.organization.federation.rules.RuleCreateParams
import com.anthropic.models.organization.federation.rules.RuleUpdateParams
import com.anthropic.models.organization.federation.rules.ServiceAccountTarget
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
        val ruleService = client.organization().federation().rules()

        val federationRule =
            ruleService.create(
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
            )

        federationRule.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.organization().federation().rules()

        val federationRule = ruleService.retrieve("federation_rule_id")

        federationRule.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.organization().federation().rules()

        val federationRule =
            ruleService.update(
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
            )

        federationRule.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleService = client.organization().federation().rules()

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
        val ruleService = client.organization().federation().rules()

        val federationRule = ruleService.archive("federation_rule_id")

        federationRule.validate()
    }
}
