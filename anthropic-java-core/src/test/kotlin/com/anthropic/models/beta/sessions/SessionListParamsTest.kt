// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SessionListParamsTest {

    @Test
    fun create() {
        SessionListParams.builder()
            .agentId("agent_id")
            .agentVersion(0)
            .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .includeArchived(true)
            .limit(0)
            .memoryStoreId("memory_store_id")
            .order(SessionListParams.Order.ASC)
            .page("page")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun headers() {
        val params =
            SessionListParams.builder()
                .agentId("agent_id")
                .agentVersion(0)
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .includeArchived(true)
                .limit(0)
                .memoryStoreId("memory_store_id")
                .order(SessionListParams.Order.ASC)
                .page("page")
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
        val params = SessionListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            SessionListParams.builder()
                .agentId("agent_id")
                .agentVersion(0)
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtGte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLte(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .includeArchived(true)
                .limit(0)
                .memoryStoreId("memory_store_id")
                .order(SessionListParams.Order.ASC)
                .page("page")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("agent_id", "agent_id")
                    .put("agent_version", "0")
                    .put("created_at[gt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[gte]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lte]", "2019-12-27T18:11:19.117Z")
                    .put("include_archived", "true")
                    .put("limit", "0")
                    .put("memory_store_id", "memory_store_id")
                    .put("order", "asc")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = SessionListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
