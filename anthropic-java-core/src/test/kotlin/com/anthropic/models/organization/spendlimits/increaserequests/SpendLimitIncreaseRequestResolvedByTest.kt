package com.anthropic.models.organization.spendlimits.increaserequests

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.organization.spendlimits.SpendLimitScopedApiKeyActor
import com.anthropic.models.organization.spendlimits.SpendLimitUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class SpendLimitIncreaseRequestResolvedByTest {

    @Test
    fun ofUserActor() {
        val userActor =
            SpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val spendLimitIncreaseRequestResolvedBy =
            SpendLimitIncreaseRequestResolvedBy.ofUserActor(userActor)

        assertThat(spendLimitIncreaseRequestResolvedBy.userActor()).contains(userActor)
        assertThat(spendLimitIncreaseRequestResolvedBy.scopedApiKeyActor()).isEmpty
    }

    @Test
    fun ofUserActorRoundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitIncreaseRequestResolvedBy =
            SpendLimitIncreaseRequestResolvedBy.ofUserActor(
                SpendLimitUserActor.builder()
                    .deleted(true)
                    .emailAddress("email_address")
                    .name("name")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        val roundtrippedSpendLimitIncreaseRequestResolvedBy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitIncreaseRequestResolvedBy),
                jacksonTypeRef<SpendLimitIncreaseRequestResolvedBy>(),
            )

        assertThat(roundtrippedSpendLimitIncreaseRequestResolvedBy)
            .isEqualTo(spendLimitIncreaseRequestResolvedBy)
    }

    @Test
    fun ofScopedApiKeyActor() {
        val scopedApiKeyActor = SpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        val spendLimitIncreaseRequestResolvedBy =
            SpendLimitIncreaseRequestResolvedBy.ofScopedApiKeyActor(scopedApiKeyActor)

        assertThat(spendLimitIncreaseRequestResolvedBy.userActor()).isEmpty
        assertThat(spendLimitIncreaseRequestResolvedBy.scopedApiKeyActor())
            .contains(scopedApiKeyActor)
    }

    @Test
    fun ofScopedApiKeyActorRoundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitIncreaseRequestResolvedBy =
            SpendLimitIncreaseRequestResolvedBy.ofScopedApiKeyActor(
                SpendLimitScopedApiKeyActor.of("scoped_api_key_id")
            )

        val roundtrippedSpendLimitIncreaseRequestResolvedBy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitIncreaseRequestResolvedBy),
                jacksonTypeRef<SpendLimitIncreaseRequestResolvedBy>(),
            )

        assertThat(roundtrippedSpendLimitIncreaseRequestResolvedBy)
            .isEqualTo(spendLimitIncreaseRequestResolvedBy)
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
        val spendLimitIncreaseRequestResolvedBy =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<SpendLimitIncreaseRequestResolvedBy>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                spendLimitIncreaseRequestResolvedBy.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
