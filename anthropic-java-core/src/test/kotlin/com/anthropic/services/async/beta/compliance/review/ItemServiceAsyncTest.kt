package com.anthropic.services.async.beta.compliance.review

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.items.ItemRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ItemServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val itemServiceAsync =
            client.beta().compliance().review().items().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaComplianceReviewItemFuture =
            itemServiceAsync.retrieve(
                ItemRetrieveParams.builder()
                    .itemId("item_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .userProfileId("anthropic-user-profile-id")
                    .build()
            )

        val betaComplianceReviewItem = betaComplianceReviewItemFuture.get()
        betaComplianceReviewItem.validate()
    }
}
