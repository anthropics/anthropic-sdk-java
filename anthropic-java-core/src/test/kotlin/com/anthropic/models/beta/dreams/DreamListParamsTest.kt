// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DreamListParamsTest {

    @Test
    fun create() {
        DreamListParams.builder()
            .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .includeArchived(true)
            .limit(0)
            .page("page")
            .addStatus(BetaDreamStatus.PENDING)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun headers() {
        val params =
            DreamListParams.builder()
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .includeArchived(true)
                .limit(0)
                .page("page")
                .addStatus(BetaDreamStatus.PENDING)
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
        val params = DreamListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            DreamListParams.builder()
                .createdAtGt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAtLt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .includeArchived(true)
                .limit(0)
                .page("page")
                .addStatus(BetaDreamStatus.PENDING)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("created_at[gt]", "2019-12-27T18:11:19.117Z")
                    .put("created_at[lt]", "2019-12-27T18:11:19.117Z")
                    .put("include_archived", "true")
                    .put("limit", "0")
                    .put("page", "page")
                    .put("statuses[]", "pending")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = DreamListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
