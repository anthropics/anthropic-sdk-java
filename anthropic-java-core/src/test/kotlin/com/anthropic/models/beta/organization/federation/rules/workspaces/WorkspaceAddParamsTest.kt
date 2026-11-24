package com.anthropic.models.beta.organization.federation.rules.workspaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceAddParamsTest {

    @Test
    fun create() {
        WorkspaceAddParams.builder()
            .federationRuleId("federation_rule_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("federation_rule_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
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
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            WorkspaceAddParams.builder()
                .federationRuleId("federation_rule_id")
                .workspaceId("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
    }
}
