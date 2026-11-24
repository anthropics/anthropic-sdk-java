package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaComplianceReviewCaseStatusTest {

    @Test
    fun ofOpen() {
        val open = BetaComplianceReviewCaseOpen.builder().build()

        val betaComplianceReviewCaseStatus = BetaComplianceReviewCaseStatus.ofOpen(open)

        assertThat(presentVariants(betaComplianceReviewCaseStatus))
            .isEqualTo(mapOf("open" to Optional.of(open)))
    }

    @Test
    fun ofOpenRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewCaseStatus =
            BetaComplianceReviewCaseStatus.ofOpen(BetaComplianceReviewCaseOpen.builder().build())

        val roundtrippedBetaComplianceReviewCaseStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewCaseStatus),
                jacksonTypeRef<BetaComplianceReviewCaseStatus>(),
            )

        assertThat(roundtrippedBetaComplianceReviewCaseStatus)
            .isEqualTo(betaComplianceReviewCaseStatus)
    }

    @Test
    fun ofClosed() {
        val closed =
            BetaComplianceReviewCaseClosed.of(
                BetaComplianceReviewCaseCloseReason.of(
                    BetaComplianceReviewCaseCloseReason.Type.RESPONDED
                )
            )

        val betaComplianceReviewCaseStatus = BetaComplianceReviewCaseStatus.ofClosed(closed)

        assertThat(presentVariants(betaComplianceReviewCaseStatus))
            .isEqualTo(mapOf("closed" to Optional.of(closed)))
    }

    @Test
    fun ofClosedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewCaseStatus =
            BetaComplianceReviewCaseStatus.ofClosed(
                BetaComplianceReviewCaseClosed.of(
                    BetaComplianceReviewCaseCloseReason.of(
                        BetaComplianceReviewCaseCloseReason.Type.RESPONDED
                    )
                )
            )

        val roundtrippedBetaComplianceReviewCaseStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewCaseStatus),
                jacksonTypeRef<BetaComplianceReviewCaseStatus>(),
            )

        assertThat(roundtrippedBetaComplianceReviewCaseStatus)
            .isEqualTo(betaComplianceReviewCaseStatus)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaComplianceReviewCaseStatus =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaComplianceReviewCaseStatus>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaComplianceReviewCaseStatus.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaComplianceReviewCaseStatus: BetaComplianceReviewCaseStatus
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "open" to betaComplianceReviewCaseStatus.open(),
                "closed" to betaComplianceReviewCaseStatus.closed(),
            )
            .filterValues { it.isPresent }
}
