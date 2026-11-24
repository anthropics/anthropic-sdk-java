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

internal class BetaComplianceReviewContentUnavailableReasonTest {

    @Test
    fun ofNotCaptured() {
        val notCaptured = BetaComplianceReviewContentNotCaptured.builder().build()

        val betaComplianceReviewContentUnavailableReason =
            BetaComplianceReviewContentUnavailableReason.ofNotCaptured(notCaptured)

        assertThat(presentVariants(betaComplianceReviewContentUnavailableReason))
            .isEqualTo(mapOf("notCaptured" to Optional.of(notCaptured)))
    }

    @Test
    fun ofNotCapturedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewContentUnavailableReason =
            BetaComplianceReviewContentUnavailableReason.ofNotCaptured(
                BetaComplianceReviewContentNotCaptured.builder().build()
            )

        val roundtrippedBetaComplianceReviewContentUnavailableReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewContentUnavailableReason),
                jacksonTypeRef<BetaComplianceReviewContentUnavailableReason>(),
            )

        assertThat(roundtrippedBetaComplianceReviewContentUnavailableReason)
            .isEqualTo(betaComplianceReviewContentUnavailableReason)
    }

    @Test
    fun ofStoredCopyUnavailable() {
        val storedCopyUnavailable = BetaComplianceReviewStoredCopyUnavailable.builder().build()

        val betaComplianceReviewContentUnavailableReason =
            BetaComplianceReviewContentUnavailableReason.ofStoredCopyUnavailable(
                storedCopyUnavailable
            )

        assertThat(presentVariants(betaComplianceReviewContentUnavailableReason))
            .isEqualTo(mapOf("storedCopyUnavailable" to Optional.of(storedCopyUnavailable)))
    }

    @Test
    fun ofStoredCopyUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewContentUnavailableReason =
            BetaComplianceReviewContentUnavailableReason.ofStoredCopyUnavailable(
                BetaComplianceReviewStoredCopyUnavailable.builder().build()
            )

        val roundtrippedBetaComplianceReviewContentUnavailableReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewContentUnavailableReason),
                jacksonTypeRef<BetaComplianceReviewContentUnavailableReason>(),
            )

        assertThat(roundtrippedBetaComplianceReviewContentUnavailableReason)
            .isEqualTo(betaComplianceReviewContentUnavailableReason)
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
        val betaComplianceReviewContentUnavailableReason =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaComplianceReviewContentUnavailableReason>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaComplianceReviewContentUnavailableReason.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaComplianceReviewContentUnavailableReason: BetaComplianceReviewContentUnavailableReason
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "notCaptured" to betaComplianceReviewContentUnavailableReason.notCaptured(),
                "storedCopyUnavailable" to
                    betaComplianceReviewContentUnavailableReason.storedCopyUnavailable(),
            )
            .filterValues { it.isPresent }
}
