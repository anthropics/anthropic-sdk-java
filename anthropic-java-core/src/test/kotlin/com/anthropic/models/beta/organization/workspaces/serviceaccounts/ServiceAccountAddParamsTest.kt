// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.serviceaccounts

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountAddParamsTest {

    @Test
    fun create() {
        ServiceAccountAddParams.builder()
            .workspaceId("workspace_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .serviceAccountId("service_account_id")
            .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .serviceAccountId("service_account_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .serviceAccountId("service_account_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
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
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .serviceAccountId("service_account_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .serviceAccountId("service_account_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.serviceAccountId()).isEqualTo("service_account_id")
        assertThat(body.workspaceRole()).isEqualTo(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .serviceAccountId("service_account_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.serviceAccountId()).isEqualTo("service_account_id")
        assertThat(body.workspaceRole()).isEqualTo(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }
}
