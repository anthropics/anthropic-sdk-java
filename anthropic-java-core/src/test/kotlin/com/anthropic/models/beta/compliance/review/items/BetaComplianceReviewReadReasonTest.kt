package com.anthropic.models.beta.compliance.review.items

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

internal class BetaComplianceReviewReadReasonTest {

    @Test
    fun ofSafeguardsReview() {
        val safeguardsReview = BetaComplianceReviewSafeguardsReason.builder().build()

        val betaComplianceReviewReadReason =
            BetaComplianceReviewReadReason.ofSafeguardsReview(safeguardsReview)

        assertThat(presentVariants(betaComplianceReviewReadReason))
            .isEqualTo(mapOf("safeguardsReview" to Optional.of(safeguardsReview)))
    }

    @Test
    fun ofSafeguardsReviewRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewReadReason =
            BetaComplianceReviewReadReason.ofSafeguardsReview(
                BetaComplianceReviewSafeguardsReason.builder().build()
            )

        val roundtrippedBetaComplianceReviewReadReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewReadReason),
                jacksonTypeRef<BetaComplianceReviewReadReason>(),
            )

        assertThat(roundtrippedBetaComplianceReviewReadReason)
            .isEqualTo(betaComplianceReviewReadReason)
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
        val betaComplianceReviewReadReason =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaComplianceReviewReadReason>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaComplianceReviewReadReason.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaComplianceReviewReadReason: BetaComplianceReviewReadReason
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "safeguardsReview" to betaComplianceReviewReadReason.safeguardsReview()
            )
            .filterValues { it.isPresent }
}
