package com.anthropic.models.beta.organization.spendlimits.increaserequests

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitScopedApiKeyActor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaSpendLimitIncreaseRequestResolvedByTest {

    @Test
    fun ofUserActor() {
        val userActor =
            BetaSpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val betaSpendLimitIncreaseRequestResolvedBy =
            BetaSpendLimitIncreaseRequestResolvedBy.ofUserActor(userActor)

        assertThat(betaSpendLimitIncreaseRequestResolvedBy.userActor()).contains(userActor)
        assertThat(betaSpendLimitIncreaseRequestResolvedBy.scopedApiKeyActor()).isEmpty
    }

    @Test
    fun ofUserActorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitIncreaseRequestResolvedBy =
            BetaSpendLimitIncreaseRequestResolvedBy.ofUserActor(
                BetaSpendLimitUserActor.builder()
                    .deleted(true)
                    .emailAddress("email_address")
                    .name("name")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        val roundtrippedBetaSpendLimitIncreaseRequestResolvedBy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitIncreaseRequestResolvedBy),
                jacksonTypeRef<BetaSpendLimitIncreaseRequestResolvedBy>(),
            )

        assertThat(roundtrippedBetaSpendLimitIncreaseRequestResolvedBy)
            .isEqualTo(betaSpendLimitIncreaseRequestResolvedBy)
    }

    @Test
    fun ofScopedApiKeyActor() {
        val scopedApiKeyActor = BetaSpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        val betaSpendLimitIncreaseRequestResolvedBy =
            BetaSpendLimitIncreaseRequestResolvedBy.ofScopedApiKeyActor(scopedApiKeyActor)

        assertThat(betaSpendLimitIncreaseRequestResolvedBy.userActor()).isEmpty
        assertThat(betaSpendLimitIncreaseRequestResolvedBy.scopedApiKeyActor())
            .contains(scopedApiKeyActor)
    }

    @Test
    fun ofScopedApiKeyActorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitIncreaseRequestResolvedBy =
            BetaSpendLimitIncreaseRequestResolvedBy.ofScopedApiKeyActor(
                BetaSpendLimitScopedApiKeyActor.of("scoped_api_key_id")
            )

        val roundtrippedBetaSpendLimitIncreaseRequestResolvedBy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitIncreaseRequestResolvedBy),
                jacksonTypeRef<BetaSpendLimitIncreaseRequestResolvedBy>(),
            )

        assertThat(roundtrippedBetaSpendLimitIncreaseRequestResolvedBy)
            .isEqualTo(betaSpendLimitIncreaseRequestResolvedBy)
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
        val betaSpendLimitIncreaseRequestResolvedBy =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaSpendLimitIncreaseRequestResolvedBy>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaSpendLimitIncreaseRequestResolvedBy.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
