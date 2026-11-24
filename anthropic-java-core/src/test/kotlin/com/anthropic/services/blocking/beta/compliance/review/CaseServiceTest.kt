package com.anthropic.services.blocking.beta.compliance.review

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewDetermination
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewVerdictParam
import com.anthropic.models.beta.compliance.review.cases.BetaComplianceReviewViolatedPolicyCategory
import com.anthropic.models.beta.compliance.review.cases.CaseRespondParams
import com.anthropic.models.beta.compliance.review.cases.CaseRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CaseServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val caseService =
            client.beta().compliance().review().cases().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaComplianceReviewCase =
            caseService.retrieve(
                CaseRetrieveParams.builder()
                    .caseId("case_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaComplianceReviewCase.validate()
    }

    @Test
    fun list() {
        val caseService =
            client.beta().compliance().review().cases().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = caseService.list()

        page.response().validate()
    }

    @Test
    fun respond() {
        val caseService =
            client.beta().compliance().review().cases().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaComplianceReviewCase =
            caseService.respond(
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

        betaComplianceReviewCase.validate()
    }
}
