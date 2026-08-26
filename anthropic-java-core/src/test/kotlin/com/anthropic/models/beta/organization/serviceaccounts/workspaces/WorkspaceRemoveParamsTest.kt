// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts.workspaces

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRemoveParamsTest {

    @Test
    fun create() {
        WorkspaceRemoveParams.builder()
            .serviceAccountId("service_account_id")
            .workspaceId("workspace_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceRemoveParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("service_account_id")
        assertThat(params._pathParam(1)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            WorkspaceRemoveParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
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
            WorkspaceRemoveParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
