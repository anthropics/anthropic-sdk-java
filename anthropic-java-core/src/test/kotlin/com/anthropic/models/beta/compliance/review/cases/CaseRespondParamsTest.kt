package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CaseRespondParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun pathParams() {
        val params =
            CaseRespondParams.builder()
                .caseId("case_id")
                .determination(BetaComplianceReviewDetermination.NO_VIOLATION)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("case_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            CaseRespondParams.builder()
                .caseId("case_id")
                .determination(BetaComplianceReviewDetermination.NO_VIOLATION)
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.determination()).isEqualTo(BetaComplianceReviewDetermination.NO_VIOLATION)
        assertThat(body.policyCategories().getOrNull())
            .containsExactly(BetaComplianceReviewViolatedPolicyCategory.CYBER)
        assertThat(body.verdicts().getOrNull())
            .containsExactly(
                BetaComplianceReviewVerdictParam.builder()
                    .determination(BetaComplianceReviewDetermination.VIOLATION)
                    .itemId("item_id")
                    .policyCategory(BetaComplianceReviewViolatedPolicyCategory.CYBER)
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            CaseRespondParams.builder()
                .caseId("case_id")
                .determination(BetaComplianceReviewDetermination.NO_VIOLATION)
                .build()

        val body = params._body()

        assertThat(body.determination()).isEqualTo(BetaComplianceReviewDetermination.NO_VIOLATION)
    }
}
