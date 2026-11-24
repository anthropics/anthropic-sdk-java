package com.anthropic.models.organization.spendlimits.increaserequests

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.organization.spendlimits.SpendLimitScopedApiKeyActor
import com.anthropic.models.organization.spendlimits.SpendLimitUserActor
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class SpendLimitIncreaseRequestActorTest {

    @Test
    fun ofUser() {
        val user =
            SpendLimitUserActor.builder()
                .deleted(true)
                .emailAddress("email_address")
                .name("name")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val spendLimitIncreaseRequestActor = SpendLimitIncreaseRequestActor.ofUser(user)

        assertThat(presentVariants(spendLimitIncreaseRequestActor))
            .isEqualTo(mapOf("user" to Optional.of(user)))
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitIncreaseRequestActor =
            SpendLimitIncreaseRequestActor.ofUser(
                SpendLimitUserActor.builder()
                    .deleted(true)
                    .emailAddress("email_address")
                    .name("name")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        val roundtrippedSpendLimitIncreaseRequestActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitIncreaseRequestActor),
                jacksonTypeRef<SpendLimitIncreaseRequestActor>(),
            )

        assertThat(roundtrippedSpendLimitIncreaseRequestActor)
            .isEqualTo(spendLimitIncreaseRequestActor)
    }

    @Test
    fun ofScopedApiKey() {
        val scopedApiKey = SpendLimitScopedApiKeyActor.of("scoped_api_key_id")

        val spendLimitIncreaseRequestActor =
            SpendLimitIncreaseRequestActor.ofScopedApiKey(scopedApiKey)

        assertThat(presentVariants(spendLimitIncreaseRequestActor))
            .isEqualTo(mapOf("scopedApiKey" to Optional.of(scopedApiKey)))
    }

    @Test
    fun ofScopedApiKeyRoundtrip() {
        val jsonMapper = jsonMapper()
        val spendLimitIncreaseRequestActor =
            SpendLimitIncreaseRequestActor.ofScopedApiKey(
                SpendLimitScopedApiKeyActor.of("scoped_api_key_id")
            )

        val roundtrippedSpendLimitIncreaseRequestActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(spendLimitIncreaseRequestActor),
                jacksonTypeRef<SpendLimitIncreaseRequestActor>(),
            )

        assertThat(roundtrippedSpendLimitIncreaseRequestActor)
            .isEqualTo(spendLimitIncreaseRequestActor)
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
        val spendLimitIncreaseRequestActor =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<SpendLimitIncreaseRequestActor>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                spendLimitIncreaseRequestActor.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        spendLimitIncreaseRequestActor: SpendLimitIncreaseRequestActor
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "user" to spendLimitIncreaseRequestActor.user(),
                "scopedApiKey" to spendLimitIncreaseRequestActor.scopedApiKey(),
            )
            .filterValues { it.isPresent }
}
