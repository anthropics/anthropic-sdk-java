package com.anthropic.services.blocking.beta.compliance.review

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.items.ItemRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ItemServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val itemService =
            client.beta().compliance().review().items().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaComplianceReviewItem =
            itemService.retrieve(
                ItemRetrieveParams.builder()
                    .itemId("item_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .userProfileId("anthropic-user-profile-id")
                    .build()
            )

        betaComplianceReviewItem.validate()
    }
}
