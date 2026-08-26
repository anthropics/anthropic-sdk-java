// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts.workspaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceAddParamsTest {

    @Test
    fun create() {
        WorkspaceAddParams.builder()
            .serviceAccountId("service_account_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .workspaceId("workspace_id")
            .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("service_account_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("workspace_id")
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
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("workspace_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("workspace_id")
        assertThat(body.workspaceRole()).isEqualTo(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("workspace_id")
        assertThat(body.workspaceRole()).isEqualTo(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }
}
