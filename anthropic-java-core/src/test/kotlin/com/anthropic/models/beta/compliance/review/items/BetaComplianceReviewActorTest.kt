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

internal class BetaComplianceReviewActorTest {

    @Test
    fun ofUser() {
        val user =
            BetaComplianceReviewUserActor.builder()
                .emailAddress("email_address")
                .userId("user_id")
                .build()

        val betaComplianceReviewActor = BetaComplianceReviewActor.ofUser(user)

        assertThat(presentVariants(betaComplianceReviewActor))
            .isEqualTo(mapOf("user" to Optional.of(user)))
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewActor =
            BetaComplianceReviewActor.ofUser(
                BetaComplianceReviewUserActor.builder()
                    .emailAddress("email_address")
                    .userId("user_id")
                    .build()
            )

        val roundtrippedBetaComplianceReviewActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewActor),
                jacksonTypeRef<BetaComplianceReviewActor>(),
            )

        assertThat(roundtrippedBetaComplianceReviewActor).isEqualTo(betaComplianceReviewActor)
    }

    @Test
    fun ofApi() {
        val api = BetaComplianceReviewApiActor.of("api_key_id")

        val betaComplianceReviewActor = BetaComplianceReviewActor.ofApi(api)

        assertThat(presentVariants(betaComplianceReviewActor))
            .isEqualTo(mapOf("api" to Optional.of(api)))
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewActor =
            BetaComplianceReviewActor.ofApi(BetaComplianceReviewApiActor.of("api_key_id"))

        val roundtrippedBetaComplianceReviewActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewActor),
                jacksonTypeRef<BetaComplianceReviewActor>(),
            )

        assertThat(roundtrippedBetaComplianceReviewActor).isEqualTo(betaComplianceReviewActor)
    }

    @Test
    fun ofServiceAccount() {
        val serviceAccount = BetaComplianceReviewServiceAccountActor.of("service_account_id")

        val betaComplianceReviewActor = BetaComplianceReviewActor.ofServiceAccount(serviceAccount)

        assertThat(presentVariants(betaComplianceReviewActor))
            .isEqualTo(mapOf("serviceAccount" to Optional.of(serviceAccount)))
    }

    @Test
    fun ofServiceAccountRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewActor =
            BetaComplianceReviewActor.ofServiceAccount(
                BetaComplianceReviewServiceAccountActor.of("service_account_id")
            )

        val roundtrippedBetaComplianceReviewActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewActor),
                jacksonTypeRef<BetaComplianceReviewActor>(),
            )

        assertThat(roundtrippedBetaComplianceReviewActor).isEqualTo(betaComplianceReviewActor)
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
        val betaComplianceReviewActor =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaComplianceReviewActor>())

        val e = assertThrows<AnthropicInvalidDataException> { betaComplianceReviewActor.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaComplianceReviewActor: BetaComplianceReviewActor
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "user" to betaComplianceReviewActor.user(),
                "api" to betaComplianceReviewActor.api(),
                "serviceAccount" to betaComplianceReviewActor.serviceAccount(),
            )
            .filterValues { it.isPresent }
}
