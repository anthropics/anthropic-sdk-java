package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ItemRetrieveParamsTest {

    @Test
    fun create() {
        ItemRetrieveParams.builder()
            .itemId("item_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .userProfileId("anthropic-user-profile-id")
            .build()
    }

    @Test
    fun pathParams() {
        val params = ItemRetrieveParams.builder().itemId("item_id").build()

        assertThat(params._pathParam(0)).isEqualTo("item_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ItemRetrieveParams.builder()
                .itemId("item_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .userProfileId("anthropic-user-profile-id")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .put("anthropic-user-profile-id", "anthropic-user-profile-id")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = ItemRetrieveParams.builder().itemId("item_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
