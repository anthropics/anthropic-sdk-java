// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaOutputBehaviorTest {

    @Test
    fun ofCreateNew() {
        val createNew = BetaOutputBehaviorCreateNew.of(BetaOutputBehaviorCreateNew.Type.CREATE_NEW)

        val betaOutputBehavior = BetaOutputBehavior.ofCreateNew(createNew)

        assertThat(betaOutputBehavior.createNew()).contains(createNew)
        assertThat(betaOutputBehavior.updateExisting()).isEmpty
    }

    @Test
    fun ofCreateNewRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputBehavior =
            BetaOutputBehavior.ofCreateNew(
                BetaOutputBehaviorCreateNew.of(BetaOutputBehaviorCreateNew.Type.CREATE_NEW)
            )

        val roundtrippedBetaOutputBehavior =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputBehavior),
                jacksonTypeRef<BetaOutputBehavior>(),
            )

        assertThat(roundtrippedBetaOutputBehavior).isEqualTo(betaOutputBehavior)
    }

    @Test
    fun ofUpdateExisting() {
        val updateExisting =
            BetaOutputBehaviorUpdateExisting.builder()
                .memoryStoreId("x")
                .type(BetaOutputBehaviorUpdateExisting.Type.UPDATE_EXISTING)
                .build()

        val betaOutputBehavior = BetaOutputBehavior.ofUpdateExisting(updateExisting)

        assertThat(betaOutputBehavior.createNew()).isEmpty
        assertThat(betaOutputBehavior.updateExisting()).contains(updateExisting)
    }

    @Test
    fun ofUpdateExistingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputBehavior =
            BetaOutputBehavior.ofUpdateExisting(
                BetaOutputBehaviorUpdateExisting.builder()
                    .memoryStoreId("x")
                    .type(BetaOutputBehaviorUpdateExisting.Type.UPDATE_EXISTING)
                    .build()
            )

        val roundtrippedBetaOutputBehavior =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputBehavior),
                jacksonTypeRef<BetaOutputBehavior>(),
            )

        assertThat(roundtrippedBetaOutputBehavior).isEqualTo(betaOutputBehavior)
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
        val betaOutputBehavior =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaOutputBehavior>())

        val e = assertThrows<AnthropicInvalidDataException> { betaOutputBehavior.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
