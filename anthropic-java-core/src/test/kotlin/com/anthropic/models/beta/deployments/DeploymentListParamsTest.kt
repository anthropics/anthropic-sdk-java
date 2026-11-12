package com.anthropic.models.beta.deployments

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeploymentListParamsTest {

    @Test
    fun create() {
        DeploymentListParams.builder()
            .agentId("agent_id")
            .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .includeArchived(true)
            .limit(0)
            .page("page")
            .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun headers() {
        val params =
            DeploymentListParams.builder()
                .agentId("agent_id")
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .includeArchived(true)
                .limit(0)
                .page("page")
                .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "managed-agents-2026-04-01")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = DeploymentListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            DeploymentListParams.builder()
                .agentId("agent_id")
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .includeArchived(true)
                .limit(0)
                .page("page")
                .status(BetaManagedAgentsDeploymentStatus.ACTIVE)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("agent_id", "agent_id")
                    .put("created_at[gte]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lte]", "2019-12-27T18:11:19.117Z")
                    .put("include_archived", "true")
                    .put("limit", "0")
                    .put("page", "page")
                    .put("status", "active")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = DeploymentListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
