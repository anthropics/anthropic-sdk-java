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

internal class BetaSpendLimitIncreaseRequestActorTest {

    @Test
    fun ofUser() {
        val user =
            BetaSpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val betaSpendLimitIncreaseRequestActor = BetaSpendLimitIncreaseRequestActor.ofUser(user)

        assertThat(betaSpendLimitIncreaseRequestActor.user()).contains(user)
        assertThat(betaSpendLimitIncreaseRequestActor.scopedApiKey()).isEmpty
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitIncreaseRequestActor =
            BetaSpendLimitIncreaseRequestActor.ofUser(
                BetaSpendLimitUserActor.builder()
                    .deleted(true)
                    .emailAddress("email_address")
                    .name("name")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        val roundtrippedBetaSpendLimitIncreaseRequestActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitIncreaseRequestActor),
                jacksonTypeRef<BetaSpendLimitIncreaseRequestActor>(),
            )

        assertThat(roundtrippedBetaSpendLimitIncreaseRequestActor)
            .isEqualTo(betaSpendLimitIncreaseRequestActor)
    }

    @Test
    fun ofScopedApiKey() {
        val scopedApiKey = BetaSpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        val betaSpendLimitIncreaseRequestActor =
            BetaSpendLimitIncreaseRequestActor.ofScopedApiKey(scopedApiKey)

        assertThat(betaSpendLimitIncreaseRequestActor.user()).isEmpty
        assertThat(betaSpendLimitIncreaseRequestActor.scopedApiKey()).contains(scopedApiKey)
    }

    @Test
    fun ofScopedApiKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaSpendLimitIncreaseRequestActor =
            BetaSpendLimitIncreaseRequestActor.ofScopedApiKey(
                BetaSpendLimitScopedApiKeyActor.of("scoped_api_key_id")
            )

        val roundtrippedBetaSpendLimitIncreaseRequestActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSpendLimitIncreaseRequestActor),
                jacksonTypeRef<BetaSpendLimitIncreaseRequestActor>(),
            )

        assertThat(roundtrippedBetaSpendLimitIncreaseRequestActor)
            .isEqualTo(betaSpendLimitIncreaseRequestActor)
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
        val betaSpendLimitIncreaseRequestActor =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaSpendLimitIncreaseRequestActor>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaSpendLimitIncreaseRequestActor.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
