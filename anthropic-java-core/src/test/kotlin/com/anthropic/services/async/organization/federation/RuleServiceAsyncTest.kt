package com.anthropic.services.async.organization.federation

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.organization.federation.rules.FederationRuleMatch
import com.anthropic.models.organization.federation.rules.RuleCreateParams
import com.anthropic.models.organization.federation.rules.RuleUpdateParams
import com.anthropic.models.organization.federation.rules.ServiceAccountTarget
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RuleServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleServiceAsync = client.organization().federation().rules()

        val federationRuleFuture =
            ruleServiceAsync.create(
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

        val federationRule = federationRuleFuture.get()
        federationRule.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleServiceAsync = client.organization().federation().rules()

        val federationRuleFuture = ruleServiceAsync.retrieve("federation_rule_id")

        val federationRule = federationRuleFuture.get()
        federationRule.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleServiceAsync = client.organization().federation().rules()

        val federationRuleFuture =
            ruleServiceAsync.update(
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

        val federationRule = federationRuleFuture.get()
        federationRule.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleServiceAsync = client.organization().federation().rules()

        val pageFuture = ruleServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val ruleServiceAsync = client.organization().federation().rules()

        val federationRuleFuture = ruleServiceAsync.archive("federation_rule_id")

        val federationRule = federationRuleFuture.get()
        federationRule.validate()
    }
}
