package com.anthropic.services.async.beta.compliance.review

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewDetermination
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewVerdictParam
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewViolatedPolicyCategory
import com.anthropic.models.beta.compliance.review.cases.CaseRespondParams
import com.anthropic.models.beta.compliance.review.cases.CaseRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CaseServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val caseServiceAsync =
            client.beta().compliance().review().cases().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaComplianceReviewCaseFuture =
            caseServiceAsync.retrieve(
                CaseRetrieveParams.builder()
                    .caseId("case_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaComplianceReviewCase = betaComplianceReviewCaseFuture.get()
        betaComplianceReviewCase.validate()
    }

    @Test
    fun list() {
        val caseServiceAsync =
            client.beta().compliance().review().cases().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = caseServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun respond() {
        val caseServiceAsync =
            client.beta().compliance().review().cases().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaComplianceReviewCaseFuture =
            caseServiceAsync.respond(
                CaseRespondParams.builder()
                    .caseId("case_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .determination(BetaComplianceReviewDetermination.NO_VIOLATION)
                    .addPolicyCategory(BetaComplianceReviewViolatedPolicyCategory.CYBER)
                    .addVerdict(
                        BetaComplianceReviewVerdictParam.builder()
                            .determination(BetaComplianceReviewDetermination.VIOLATION)
                            .itemId("item_id")
                            .policyCategory(BetaComplianceReviewViolatedPolicyCategory.CYBER)
                            .build()
                    )
                    .build()
            )

        val betaComplianceReviewCase = betaComplianceReviewCaseFuture.get()
        betaComplianceReviewCase.validate()
    }
}
